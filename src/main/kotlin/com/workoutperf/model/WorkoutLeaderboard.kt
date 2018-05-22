package com.workoutperf.model

data class WorkoutLeaderboard(
        override val id: String?,
        override val division: Group,
        override val positions: MutableSet<WorkoutPosition>,
        override val period: Period,
        val workout: Workout,
        override val acl: Acl
) : AclObject, Leaderboard<WorkoutPosition> {
    init {
        this.positions.map { position ->
            position.workout = this.workout
            position.leaderboard = this }
    }
}