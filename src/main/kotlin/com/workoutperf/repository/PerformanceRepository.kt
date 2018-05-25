package com.workoutperf.repository

import com.workoutperf.entity.Performance
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PerformanceRepository : JpaRepository<Performance, String> {

    fun findByWorkoutId(workoutId: String): List<Performance>

}