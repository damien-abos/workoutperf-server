package com.workoutperf.entity

import javax.persistence.*

@Entity
data class WorkoutPosition(
  @Id
  val id: String? = null,
  val rank: Int = 0,
  val points: Double = 0.0
) {
  @OneToOne
  var leaderboard: WorkoutLeaderboard? = null

  @ManyToOne
  @JoinColumn(name = "performance_id")
  var performance: Performance? = null

  @ManyToOne
  @JoinColumn(name = "athlete_id")
  var athlete: Person? = null

  @ManyToOne
  @JoinColumn(name = "workout_id")
  var workout: Workout? = null

  constructor(
    id: String? = null,
    rank: Int = 0,
    points: Double = 0.0,
    leaderboard: WorkoutLeaderboard? = null,
    performance: Performance? = null,
    athlete: Person? = null,
    workout: Workout? = null
  ) : this(
    id = id,
    rank = rank,
    points = points
  ) {
    this.leaderboard = leaderboard
    this.performance = performance
    this.athlete = athlete
    this.workout = workout
  }

  constructor(workoutPosition: com.workoutperf.model.WorkoutPosition, workoutLeaderboard: WorkoutLeaderboard? = null) :
    this(
      id = workoutPosition.id,
      rank = workoutPosition.rank,
      points = workoutPosition.points

    ) {
    this.leaderboard = workoutLeaderboard
    this.performance = if (workoutPosition.performance != null) Performance(workoutPosition.performance) else null
    this.athlete = if (workoutPosition.athlete != null) Person(workoutPosition.athlete) else null
    this.workout = if (workoutPosition.workout != null) Workout(workoutPosition.workout!!) else null
  }

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

  override fun hashCode(): Int {
    return if (this.id != null) this.id.hashCode() else 0
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other == null || javaClass != other.javaClass) return false
    val that = other as WorkoutPosition?
    return id == that!!.id
  }
}
