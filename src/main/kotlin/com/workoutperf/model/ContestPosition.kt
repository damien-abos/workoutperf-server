package com.workoutperf.model

data class ContestPosition(
        override val id: String?,
        override val rank: Int,
        override val points: Double,
        override var leaderboard: ContestLeaderboard?,
        val workoutPositions: MutableMap<Workout, WorkoutPosition>,
        var contest: Contest?,
        val athlete: Person?
) : Position<ContestLeaderboard>
