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
                            .map { contestPosition -> ContestPosition(contestPosition, contestLeaderboard = null) }
                            .toCollection(LinkedHashSet(contestLeaderboard.positions.size)),
                    period = Period(contestLeaderboard.period),
                    contest = null,
                    acl = Acl(contestLeaderboard.acl)
            )

    fun toModel() =
            com.workoutperf.model.ContestLeaderboard(
                    id = this.id,
                    division = this.division!!.toModel(),
                    positions = this.positions
                            .map { contestPosition -> contestPosition.toModel(contestLeaderboard = null) }
                            .toCollection(LinkedHashSet(this.positions.size)),
                    period = this.period.toModel(),
                    contest = this.contest!!.toModel(),
                    acl = this.acl.toModel()
            )

    override fun hashCode(): Int {
        return if (this.id != null) this.id.hashCode() else 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as ContestLeaderboard?
        return id == that!!.id
    }
}