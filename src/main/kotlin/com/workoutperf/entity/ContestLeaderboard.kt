package com.workoutperf.entity

import javax.persistence.*

@Entity
data class ContestLeaderboard(
        @Id
        val id: String? = null,
        @ManyToOne
        @JoinColumn(name = "division_id")
        var division: Group? = null,
        @OneToMany(mappedBy = "leaderboard")
        val positions: MutableSet<ContestPosition> = mutableSetOf(),
        val period: Period = Period(),
        @ManyToOne
        @JoinColumn(name = "contest_id")
        var contest: Contest? = null,
        @OneToOne(cascade = [CascadeType.ALL])
        val acl: Acl = Acl()
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
                    division = this.division!!.toModel(),
                    positions = this.positions
                            .map { contestPosition -> contestPosition.toModel() }
                            .toCollection(LinkedHashSet(this.positions.size)),
                    period = this.period.toModel(),
                    contest = this.contest!!.toModel(),
                    acl = this.acl.toModel()
            )
}