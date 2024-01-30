package com.workoutperf.model

data class Workout(
  val id: String?,
  val name: String,
  val description: String,
  val period: Period,
  val weight: Double,
  val type: WorkoutType
)
