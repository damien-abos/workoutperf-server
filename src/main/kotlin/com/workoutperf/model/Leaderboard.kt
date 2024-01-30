package com.workoutperf.model

interface Leaderboard<P> {
  val id: String?
  val division: Group
  val positions: MutableSet<P>
  val period: Period
}
