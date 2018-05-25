package com.workoutperf.entity

import javax.persistence.*

@Entity
data class WorkoutLeaderboard(
        @Id
        val id: String? = null,

        val period: Period = Period(),

        @OneToOne(cascade = [CascadeType.ALL])
        val acl: Acl = Acl()
) {
    @ManyToOne
    @JoinColumn(name = "division_id")
    var division: Group = Group()

    @OneToMany(mappedBy = "workout")
    var positions: MutableSet<WorkoutPosition> = mutableSetOf()

    @ManyToOne
    @JoinColumn(name = "workout_id")
    var workout: Workout = Workout()

    constructor(
            id: String? = null,
            period: Period = Period(),
            division: Group = Group(),
            positions: MutableSet<WorkoutPosition> = mutableSetOf(),
            workout: Workout = Workout(),
            acl: Acl = Acl()
    ) : this(
            id = id,
            period = period,
            acl = acl
    ) {
        this.division = division
        this.positions = positions
        this.workout = workout
    }


    constructor(workoutLeaderboard: com.workoutperf.model.WorkoutLeaderboard) :
            this(
                    id = workoutLeaderboard.id,
                    period = Period(workoutLeaderboard.period),
                    acl = Acl(workoutLeaderboard.acl)
            ) {
        this.division = Group(workoutLeaderboard.division)
        this.positions = workoutLeaderboard.positions
                .map { position ->
                    WorkoutPosition(position, this)
                }
                .toCollection(LinkedHashSet(workoutLeaderboard.positions.size))
        this.workout = Workout(workoutLeaderboard.workout)
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

    override fun hashCode(): Int {
        return if (this.id != null) this.id.hashCode() else 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as WorkoutLeaderboard?
        return id == that!!.id
    }
}