package com.workoutperf.entity

import javax.persistence.*

@Entity
data class ContestPosition(
        @Id
        val id: String? = null,
        var rank: Int = 0,
        val points: Int = 0,
        @ManyToOne
        @JoinColumn(name = "athlete_id")
        val athlete: Person? = null,
        @ManyToOne
        @JoinColumn(name = "leaderboard_id")
        val leaderboard: ContestLeaderboard? = null,
        @ManyToMany
        @JoinTable(name = "contestposition_workoutposition",
                joinColumns = [JoinColumn(name = "workoutposition_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "contestposition_id", referencedColumnName = "id")])
        val workoutPositions: MutableSet<WorkoutPosition> = mutableSetOf(),
        @OneToOne(cascade = [CascadeType.ALL])
        val acl: Acl = Acl()
) {
    constructor(contestPosition: com.workoutperf.model.ContestPosition, contestLeaderboard: ContestLeaderboard? = null) :
            this(
                    id = contestPosition.id,
                    rank = contestPosition.rank,
                    points = contestPosition.points,
                    leaderboard = contestLeaderboard,
                    workoutPositions = contestPosition.workoutPositions
                            .map { workoutPositionEntry -> WorkoutPosition(workoutPositionEntry.value) }
                            .toCollection(LinkedHashSet(contestPosition.workoutPositions.size)),
                    acl = Acl(contestPosition.acl)
            )

    fun toModel(contestLeaderboard: com.workoutperf.model.ContestLeaderboard? = null) =
            com.workoutperf.model.ContestPosition(
                    id = this.id,
                    rank = this.rank,
                    points = this.points,
                    leaderboard = contestLeaderboard,
                    workoutPositions = this.workoutPositions
                            .map { workoutPosition: WorkoutPosition -> workoutPosition.toModel() }
                            .associateByTo(mutableMapOf(),
                                    { workoutPosition: com.workoutperf.model.WorkoutPosition -> workoutPosition.workout!! }),
                    contest = null,
                    acl = this.acl.toModel()
            )
}
