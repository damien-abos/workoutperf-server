package com.workoutperf.repository

import com.workoutperf.entity.WorkoutLeaderboard
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WorkoutLeaderboardRepository : JpaRepository<WorkoutLeaderboard, String>