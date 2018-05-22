package com.workoutperf.repository

import com.workoutperf.entity.Workout
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WorkoutRepository : JpaRepository<Workout, String>