package com.workoutperf.model

import java.time.Duration
import java.time.LocalDateTime

data class Performance(
        val id: String?,
        val score: Int,
        val duration: Duration,
        val date: LocalDateTime,
        //val location: Location,
        val comment: String,
        val athlete: Person,
        val judge: Person?,
        val workout: Workout? = null,
        override val acl: Acl?
) : AclObject