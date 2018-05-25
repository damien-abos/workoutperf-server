package com.workoutperf.repository

import com.workoutperf.entity.WorkoutPosition
import org.springframework.data.jpa.repository.JpaRepository

interface WorkoutPositionRepository: JpaRepository<WorkoutPosition, String> {

    fun findByWorkoutContestId(contestId: String): List<WorkoutPosition>

}