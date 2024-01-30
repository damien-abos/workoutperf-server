package com.workoutperf.model

interface Position<L> {
  val id: String?
  val rank: Int
  val points: Double
  var leaderboard: L?
}
