package com.workoutperf.endpoint

import com.workoutperf.model.ContestLeaderboard
import com.workoutperf.model.ContestPosition
import com.workoutperf.model.WorkoutPosition
import com.workoutperf.service.LeaderboardService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.time.Duration

@RestController
@RequestMapping(path = ["/contests/{contestId}/leaderboards"], produces = [(MediaType.APPLICATION_JSON_VALUE)])
class LeaderboardEndpoint(
        val leaderboardService: LeaderboardService
) {

    //
    //
    // POST /contest/{contestId}/leaderboards

    @RequestMapping(method = [RequestMethod.POST])
    fun computeWorkoutLeaderboard(
            @PathVariable("contestId") contestId: String
    ): ResponseEntity<Any> {
        leaderboardService.computeContestLeaderboards(contestId)
        return ResponseEntity.ok().build()
    }

    //
    //
    // GET /contest/{contestId}/leaderboards





    data class WorkoutPositionResponse(
            val workout: String,
            val rank: Int,
            val points: Double,
            val score: Int?,
            val time: String?
    ) {
        constructor(workoutPosition: WorkoutPosition) : this(
                workout = workoutPosition.workout!!.name,
                rank = workoutPosition.rank,
                points = workoutPosition.points,
                score = workoutPosition.performance?.score,
                time = if (workoutPosition.performance != null) formatDuration(workoutPosition.performance.duration) else "--:--"
        )

        companion object {
            fun formatDuration(duration: Duration): String {
                val s = duration.seconds
                return String.format("%02d:%02d", s / 60, s % 60)
            }
        }

    }

    data class ContestPositionResponse(
            val rank: Int,
            val points: Double,
            val athlete: String,
            val workoutPositions: MutableList<WorkoutPositionResponse>
    ) {
        constructor(contestPosition: ContestPosition) : this(
                rank = contestPosition.rank,
                points = contestPosition.points,
                athlete = contestPosition.athlete!!.name,
                workoutPositions = mutableListOf()
        ) {
            workoutPositions.addAll(contestPosition.workoutPositions.map { WorkoutPositionResponse(it.value) }.sortedBy { it.workout })
        }
    }

    data class ContestLeaderboardResponse(
            val contest: String,
            val division: String,
            val positions: MutableList<ContestPositionResponse>
    ) {

        constructor(contestLeaderboard: ContestLeaderboard) : this(
                contest = contestLeaderboard.contest.name,
                division = contestLeaderboard.division.name,
                positions = mutableListOf()
        ) {
            positions.addAll(contestLeaderboard.positions.map { ContestPositionResponse(it) }.sortedBy { it.rank })
        }
    }

    @RequestMapping(method = [RequestMethod.GET])
    fun getAllContestLeaderboards(
            @PathVariable("contestId") contestId: String
    ): ResponseEntity<Any> {
        val contestLeaderboards = leaderboardService.getAllContestLeaderboards(contestId).map {
            ContestLeaderboardResponse(it)
        }.sortedBy { it.division }
        return ResponseEntity.ok(contestLeaderboards)
    }


    //
    //
    // GET /contest/{contestId}/leaderboard/{leaderboardId}

    @RequestMapping(path = ["/{leaderboardId}"], method = [RequestMethod.GET])
    fun getWorkoutLeaderboard(
            @PathVariable("contestId") contestId: String,
            @PathVariable("leaderboardId") leaderboardId: String
    ): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build()
    }

}