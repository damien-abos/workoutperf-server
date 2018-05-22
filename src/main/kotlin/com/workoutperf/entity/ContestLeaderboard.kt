package com.workoutperf.entity

import javax.persistence.*

@Entity
data class ContestLeaderboard(
        @Id
        val id: String?,
        @OneToOne
        val division: Group,
        @OneToMany(mappedBy = "leaderboard")
        val positions: MutableSet<ContestPosition>,
        val period: Period,
        @ManyToOne
        @JoinColumn(name = "contest_id")
        val contest: Contest,
        @OneToOne(cascade = [CascadeType.ALL])
        val acl: Acl
) {
    constructor(contestLeaderboard: com.workoutperf.model.ContestLeaderboard) :
            this(
                    id = contestLeaderboard.id,
                    division = Group(contestLeaderboard.division),
                    positions = contestLeaderboard.positions
                            .map { contestPosition -> ContestPosition(contestPosition) }
                            .toCollection(LinkedHashSet(contestLeaderboard.positions.size)),
                    period = Period(contestLeaderboard.period),
                    contest = Contest(contestLeaderboard.contest),
                    acl = Acl(contestLeaderboard.acl)
            ) {
        this.positions.map { position -> position.leaderboard = this }
    }

    fun toModel() =
            com.workoutperf.model.ContestLeaderboard(
                    id = this.id,
                    division = this.division.toModel(),
                    positions = this.positions
                            .map { contestPosition -> contestPosition.toModel() }
                            .toCollection(LinkedHashSet(this.positions.size)),
                    period = this.period.toModel(),
                    contest = this.contest.toModel(),
                    acl = this.acl.toModel()
            )
}