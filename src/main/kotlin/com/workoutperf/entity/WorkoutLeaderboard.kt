package com.workoutperf.entity

import javax.persistence.*

@Entity
data class WorkoutLeaderboard(
        @Id
        val id: String? = null,
        @OneToOne
        val division: Group = Group(),
        @OneToMany(mappedBy = "workout")
        val positions: MutableSet<WorkoutPosition>,
        val period: Period = Period(),
        @OneToOne
        val workout: Workout = Workout(),
        @OneToOne(cascade = [CascadeType.ALL])
        val acl: Acl = Acl()
) {
    constructor(workoutLeaderboard: com.workoutperf.model.WorkoutLeaderboard) :
            this(
                    id = workoutLeaderboard.id,
                    division = Group(workoutLeaderboard.division),
                    positions = workoutLeaderboard.positions
                            .map { position ->
                                WorkoutPosition(position)
                            }
                            .toCollection(LinkedHashSet(workoutLeaderboard.positions.size)),
                    period = Period(workoutLeaderboard.period),
                    workout = Workout(workoutLeaderboard.workout),
                    acl = Acl(workoutLeaderboard.acl)
            ) {
        this.positions.map { position -> position.leaderboard = this }
    }

    fun toModel() =
            com.workoutperf.model.WorkoutLeaderboard(
                    id = this.id,
                    division = this.division.toModel(),
                    positions = this.positions
                            .map { position -> position.toModel() }
                            .toCollection(LinkedHashSet(this.positions.size)),
                    period = this.period.toModel(),
                    workout = this.workout.toModel(),
                    acl = this.acl.toModel()
            )

}