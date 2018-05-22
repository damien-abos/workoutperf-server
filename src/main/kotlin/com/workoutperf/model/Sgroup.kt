package com.workoutperf.model

class Sgroup : Sid {
    val sids : MutableSet<Sid>

    constructor(sid: String, sids: MutableSet<Sid>) : super(sid = sid) {
        this.sids = sids
    }

    companion object {
        val Everyone = Sgroup("Everyone", mutableSetOf())
    }
}
