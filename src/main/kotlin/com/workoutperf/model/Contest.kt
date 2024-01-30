package com.workoutperf.model

data class Contest(
  val id: String?,
  val name: String = "Contest",
  val description: String = "Lorem Ipsum",
  val period: Period = Period(),
  val workouts: MutableSet<Workout> = mutableSetOf(),
  val organizers: MutableSet<Person> = mutableSetOf(),
  val judges: MutableSet<Person> = mutableSetOf(),
  val members: MutableSet<Person> = mutableSetOf(),
  val divisions: MutableSet<Group> = mutableSetOf()
)
