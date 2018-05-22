package com.workoutperf.model

data class ContestPosition(
        override val id: String?,
        override val rank: Int,
        override val points: Int,
        override var leaderboard: ContestLeaderboard?,
        val workoutPositions: MutableMap<Workout, WorkoutPosition>,
        var contest: Contest?,
        override val acl: Acl
) : AclObject, Position<ContestLeaderboard>
