package com.workoutperf.model

data class Group(
  val id: String?,
  val name: String,
  val members: MutableSet<Person>
)
