package com.workoutperf.entity

import com.workoutperf.model.WorkoutType
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class Workout(
  @Id
  val id: String? = null,
  val name: String = "",
  val description: String = "",
  val period: Period = Period(),
  val weight: Double = 1.0,
  val type: String = "TIME_ASC_SCORE_ASC",
  @ManyToOne
  @JoinColumn(name = "contest_id")
  var contest: Contest? = null
) {
  constructor(workout: com.workoutperf.model.Workout) :
    this(
      id = workout.id,
      name = workout.name,
      description = workout.description,
      period = Period(workout.period.begin, workout.period.end),
      weight = workout.weight,
      type = workout.type.name
    )

  fun toModel(): com.workoutperf.model.Workout =
    com.workoutperf.model.Workout(
      id = this.id,
      name = this.name,
      description = this.description,
      period = com.workoutperf.model.Period(this.period.begin, this.period.end),
      weight = this.weight,
      type = WorkoutType.valueOf(this.type)
    )
}
