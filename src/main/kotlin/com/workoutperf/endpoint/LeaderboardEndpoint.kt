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
            val time: Duration?
    ) {
        constructor(workoutPosition: WorkoutPosition) : this(
                workout = workoutPosition.workout!!.name,
                rank = workoutPosition.rank,
                points = workoutPosition.points,
                score = workoutPosition.performance?.score,
                time = workoutPosition.performance?.duration
        )
    }

    data class ContestPositionResponse(
            val rank: Int,
            val points: Double,
            val athlete: String,
            val workoutPosition: MutableList<WorkoutPositionResponse>
    ) {
        constructor(contestPosition: ContestPosition) : this(
                rank = contestPosition.rank,
                points = contestPosition.points,
                athlete = contestPosition.athlete!!.name,
                workoutPosition = mutableListOf()
        ) {
            workoutPosition.addAll(contestPosition.workoutPositions.map { WorkoutPositionResponse(it.value) }.sortedBy { it.workout })
        }
    }

    data class ContestLeaderboardResponse(
            val contest: String,
            val division: String,
            val position: MutableList<ContestPositionResponse>
    ) {

        constructor(contestLeaderboard: ContestLeaderboard) : this(
                contest = contestLeaderboard.contest.name,
                division = contestLeaderboard.division.name,
                position = mutableListOf()
        ) {
            position.addAll(contestLeaderboard.positions.map { ContestPositionResponse(it) }.sortedBy { it.rank })
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