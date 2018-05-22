package com.workoutperf.model

data class Workout(
        val id: String?,
        val name: String,
        val description: String,
        val period: Period,
        override val acl: Acl
) : AclObject