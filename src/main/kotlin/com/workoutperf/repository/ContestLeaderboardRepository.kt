package com.workoutperf.repository

import com.workoutperf.entity.ContestLeaderboard
import org.springframework.data.jpa.repository.JpaRepository

interface ContestLeaderboardRepository : JpaRepository<ContestLeaderboard, String> {

    fun findAllByContestId(contestId: String): List<ContestLeaderboard>
}