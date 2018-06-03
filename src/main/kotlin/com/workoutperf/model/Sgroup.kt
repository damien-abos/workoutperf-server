package com.workoutperf.model

class Sgroup(
        override val sid: String,
        val sids: MutableSet<Sid> = mutableSetOf()
) : Sid(sid = sid)
