package com.workoutperf.model

interface Position<L> {
    val id: String?
    val rank: Int
    val points: Int
    var leaderboard: L?
}
