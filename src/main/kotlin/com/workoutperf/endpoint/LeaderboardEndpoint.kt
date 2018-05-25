package com.workoutperf.endpoint

import com.workoutperf.service.LeaderboardService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

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

    @RequestMapping(method = [RequestMethod.GET])
    fun getAllWorkoutLeaderboards(
            @PathVariable("contestId") contestId: String
    ): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build()
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