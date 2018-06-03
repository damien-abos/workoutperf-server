package com.workoutperf.model

data class ContestLeaderboard(
        override val id: String?,
        override val division: Group,
        override val positions: MutableSet<ContestPosition>,
        override val period: Period,
        val contest: Contest,
        override val acl: Acl?
) : AclObject, Leaderboard<ContestPosition> {
    init {
        this.positions.map { position ->
            position.contest = this.contest
            position.leaderboard = this }
    }
}