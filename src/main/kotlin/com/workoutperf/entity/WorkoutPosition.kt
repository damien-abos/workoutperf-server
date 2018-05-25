package com.workoutperf.entity

import javax.persistence.*

@Entity
data class WorkoutPosition(
        @Id
        val id: String? = null,
        val rank: Int = 0,
        val points: Int = 0,
        @OneToOne
        var leaderboard: WorkoutLeaderboard? = null,
        @ManyToOne
        @JoinColumn(name = "performance_id")
        val performance: Performance? = null,
        @ManyToOne
        @JoinColumn(name = "athlete_id")
        val athlete: Person? = null,
        @ManyToOne
        @JoinColumn(name = "workout_id")
        var workout: Workout? = null
) {
    constructor(workoutPosition: com.workoutperf.model.WorkoutPosition, workoutLeaderboard: WorkoutLeaderboard? = null) :
            this(
                    id = workoutPosition.id,
                    rank = workoutPosition.rank,
                    points = workoutPosition.points,
                    leaderboard = workoutLeaderboard,
                    performance =  if (workoutPosition.performance != null) Performance(workoutPosition.performance) else null,
                    athlete = if (workoutPosition.athlete != null) Person(workoutPosition.athlete) else null,
                    workout = if (workoutPosition.workout != null) Workout(workoutPosition.workout!!) else null
            )

    fun toModel(workoutLeaderboard: com.workoutperf.model.WorkoutLeaderboard? = null) =
            com.workoutperf.model.WorkoutPosition(
                    id = this.id,
                    rank = this.rank,
                    points = this.points,
                    leaderboard = workoutLeaderboard,
                    performance = this.performance?.toModel(),
                    athlete = this.athlete?.toModel(),
                    workout = this.workout?.toModel()
            )
}
