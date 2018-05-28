package com.workoutperf.service

import com.workoutperf.entity.*
import com.workoutperf.model.WorkoutType
import com.workoutperf.repository.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Service
class LeaderboardService(
        val workoutRepository: WorkoutRepository,
        val contestRepository: ContestRepository,
        val performanceRepository: PerformanceRepository,
        val workoutPositionRepository: WorkoutPositionRepository,
        val workoutLeaderboardRepository: WorkoutLeaderboardRepository,
        val contestPositionRepository: ContestPositionRepository,
        val contestLeaderboardRepository: ContestLeaderboardRepository
) {

    @Transactional(isolation = Isolation.SERIALIZABLE)
    fun computeWorkoutLeaderboard(contestId: String, workoutId: String) {
        val contest = contestRepository.findById(contestId)
        if (contest.isPresent) {
            val workout = workoutRepository.findById(workoutId)
            if (workout.isPresent && workout.get().contest!!.id == contestId) {
                val performances = performanceRepository.findByWorkoutId(workoutId)
                // sort all performances for the workout
                val positions = performances
                        .sortedWith(kotlin.Comparator { o1, o2 ->
                            val deltaDuration = o1.duration.minus(o2.duration).seconds.toInt()
                            val deltaScore = o1.score - o2.score
                            when (WorkoutType.valueOf(workout.get().type)) {
                                WorkoutType.TIME_ASC_SCORE_ASC -> if (deltaDuration == 0) deltaScore else deltaDuration
                                WorkoutType.TIME_ASC_SCORE_DESC -> if (deltaDuration == 0) -deltaScore else deltaDuration
                                WorkoutType.TIME_DESC_SCORE_ASC -> if (deltaDuration == 0) deltaScore else -deltaDuration
                                WorkoutType.TIME_DESC_SCORE_DESC -> if (deltaDuration == 0) -deltaScore else -deltaDuration
                                WorkoutType.SCORE_ASC_TIME_ASC -> if (deltaScore == 0) deltaDuration else deltaScore
                                WorkoutType.SCORE_ASC_TIME_DESC -> if (deltaScore == 0) -deltaDuration else deltaScore
                                WorkoutType.SCORE_DESC_TIME_ASC -> if (deltaScore == 0) deltaDuration else -deltaScore
                                WorkoutType.SCORE_DESC_TIME_DESC -> if (deltaScore == 0) -deltaDuration else -deltaScore
                            }
                        })
                // build a workout leaderboard per division
                val workoutLeaderboards = contest.get().divisions.map { division ->
                    WorkoutLeaderboard(
                            id = "${contestId}_${workoutId}_${division.name}",
                            division = division,
                            workout = workout.get()
                    )
                }
                // fill the leaderboards
                workoutLeaderboards.map { leaderboard ->
                    val leaderboardEntity = workoutLeaderboardRepository.save(leaderboard)
                    val divisionAthletes = leaderboard.division.members.toMutableSet()
                    var rank: Int = 1
                    var index: Int = 1
                    var lastPerformance: Performance? = null
                    // Keep performance for athletes in the leaderboard division
                    val divisionPosition = positions
                            .filter { performance ->
                                divisionAthletes.any { person ->
                                    person.id == performance.athlete!!.id
                                }
                            }
                    // Ranking over performance
                    divisionPosition.map { performance ->
                        val workoutPosition: WorkoutPosition
                        // If not first or same performance result
                        if (lastPerformance != null
                                && (lastPerformance!!.duration != performance.duration
                                        || lastPerformance!!.score != performance.score)) {
                            rank = index
                        }
                        workoutPosition = WorkoutPosition(
                                id = "${leaderboard.id}_${performance.athlete!!.id}",
                                rank = rank,
                                points = rank.toDouble(),
                                leaderboard = leaderboardEntity,
                                performance = performance,
                                athlete = performance.athlete,
                                workout = performance.workout
                        )
                        leaderboardEntity.positions.add(workoutPosition)
                        lastPerformance = performance
                        divisionAthletes.remove(performance.athlete!!)
                        index++
                        workoutPositionRepository.save(workoutPosition)
                    }
                    // Append remaining athletes with no performance
                    divisionAthletes.map { person ->
                        val workoutPosition = WorkoutPosition(
                                id = "${leaderboard.id}_${person.id}",
                                rank = index,
                                points = index.toDouble(),
                                leaderboard = leaderboardEntity,
                                performance = null,
                                athlete = person,
                                workout = workout.get()
                        )
                        leaderboardEntity.positions.add(workoutPosition)
                        workoutPositionRepository.save(workoutPosition)
                    }
                    leaderboardEntity
                }
            }
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    fun computeContestLeaderboards(contestId: String) {
        val contest = contestRepository.findById(contestId)
        if (contest.isPresent) {
            // compute WorkoutLeaderBoards
            contest.get().workouts.map { workout ->
                computeWorkoutLeaderboard(contestId, workout.id!!)
            }
            val positions = workoutPositionRepository.findByWorkoutContestId(contestId)
            val positionsByDivivion = positions.groupBy { workoutPosition ->
                workoutPosition.leaderboard!!.division.id!!
            }
            contest.get().divisions.map { division ->
                val leaderboard = ContestLeaderboard(
                        id = "${contestId}_${division.name}",
                        division = division,
                        contest = contest.get()
                )
                val leaderboardEntity = contestLeaderboardRepository.save(leaderboard)
                // prepare position per member
                val positionsByMember = if (positionsByDivivion.containsKey(division.id)) positionsByDivivion[division.id]!!.groupBy { workoutPosition ->
                    workoutPosition.athlete!!.id!!
                } else mapOf()
                // create leaderboard positions sorted by score
                val contestPositions = division.members.map { athlete ->
                    val athletePositions = positionsByMember[athlete.id]!!
                    val points = athletePositions.sumByDouble { position -> position.points * position.workout!!.weight }
                    ContestPosition(
                            id = "${leaderboard.id}_${athlete.id!!}",
                            points = points,
                            athlete = athlete,
                            workoutPositions = athletePositions.toCollection(LinkedHashSet(athletePositions.size)),
                            leaderboard = leaderboardEntity
                    )
                }.sortedBy { position -> position.points }
                // then update rank and save
                var index = 1
                var rank = 1
                var lastPosition: ContestPosition? = null
                contestPositions.map { position ->
                    if (lastPosition?.points != position.points) {
                        rank = index
                    }
                    lastPosition = position
                    index++
                    position.rank = rank
                    contestPositionRepository.save(position)
                }
            }
        }
    }

    fun getAllContestLeaderboards(contestId: String): List<com.workoutperf.model.ContestLeaderboard> {
        return contestLeaderboardRepository.findAllByContestId(contestId)
                .map { contestLeaderboard -> contestLeaderboard.toModel() }
    }

}