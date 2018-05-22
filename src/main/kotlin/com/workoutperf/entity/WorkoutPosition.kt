package com.workoutperf.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
data class WorkoutPosition(
        @Id
        val id: String? = null,
        val rank: Int = 0,
        val points: Int = 0,
        @OneToOne
        var leaderboard: WorkoutLeaderboard? = null,
        @OneToOne
        val performance: Performance = Performance(),
        @OneToOne
        var workout: Workout? = null
) {
    constructor(workoutPosition: com.workoutperf.model.WorkoutPosition, workoutLeaderboard: WorkoutLeaderboard? = null) :
            this(
                    id = workoutPosition.id,
                    rank = workoutPosition.rank,
                    points = workoutPosition.points,
                    leaderboard = workoutLeaderboard,
                    performance = Performance(workoutPosition.performance),
                    workout = if (workoutPosition.workout != null) Workout(workoutPosition.workout!!) else null
            )

    fun toModel(workoutLeaderboard: com.workoutperf.model.WorkoutLeaderboard? = null) =
            com.workoutperf.model.WorkoutPosition(
                    id = this.id,
                    rank = this.rank,
                    points = this.points,
                    leaderboard = workoutLeaderboard,
                    performance = this.performance.toModel(),
                    workout = this.workout?.toModel()
            )
}
