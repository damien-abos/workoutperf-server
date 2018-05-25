package com.workoutperf.model

data class WorkoutPosition(
        override val id: String?,
        override val rank: Int,
        override val points: Double,
        override var leaderboard: WorkoutLeaderboard?,
        val performance: Performance?,
        val athlete: Person?,
        var workout: Workout?
) : Position<WorkoutLeaderboard>
