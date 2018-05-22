package com.workoutperf.endpoint

import com.workoutperf.service.WorkoutLeaderboardService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/contests/{contestId}/workouts/{workoutId}/leaderboard"], produces = [(MediaType.APPLICATION_JSON_VALUE)])
class WorkoutLeaderboardEndpoint(
        val workoutLeaderboardService: WorkoutLeaderboardService
) {

    //
    //
    // POST /contest/{contestId}/workouts/{workoutId}/leaderboard

    @RequestMapping(method = [RequestMethod.POST])
    fun computeWorkoutLeaderboard(
            @PathVariable("contestId") contestId: String,
            @PathVariable("workoutId") workoutId: String
    ): ResponseEntity<Any> {
        workoutLeaderboardService.computeWorkoutLeaderboard(contestId, workoutId)
        return ResponseEntity.ok().build()
    }

    //
    //
    // GET /contest/{contestId}/workouts/{workoutId}/leaderboard

    @RequestMapping(method = [RequestMethod.GET])
    fun getAllWorkoutLeaderboards(
            @PathVariable("contestId") contestId: String,
            @PathVariable("workoutId") workoutId: String
    ): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build()
    }


    //
    //
    // GET /contest/{contestId}/workouts/{workoutId}/leaderboard/{leaderboardId}

    @RequestMapping(path = ["/{leaderboardId}"], method = [RequestMethod.GET])
    fun getWorkoutLeaderboard(
            @PathVariable("contestId") contestId: String,
            @PathVariable("workoutId") workoutId: String,
            @PathVariable("leaderboardId") leaderboardId: String
    ): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build()
    }

}