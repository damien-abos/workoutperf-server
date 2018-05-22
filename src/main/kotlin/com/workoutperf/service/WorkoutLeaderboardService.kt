package com.workoutperf.service

import com.workoutperf.model.WorkoutLeaderboard
import com.workoutperf.repository.PerformanceRepository
import com.workoutperf.repository.WorkoutLeaderboardRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class WorkoutLeaderboardService(
        val workoutService: WorkoutService,
        val performanceRepository: PerformanceRepository,
        val leaderboardRepository: WorkoutLeaderboardRepository
) {

    fun computeWorkoutLeaderboard(contestId: String, workoutId: String): Optional<WorkoutLeaderboard> {
        val workout = workoutService.getWorkout(contestId, contestId)
        return if (workout.isPresent) {

            Optional.empty()
        } else {
            Optional.empty()
        }
    }
}