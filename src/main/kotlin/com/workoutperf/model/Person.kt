package com.workoutperf.model

import java.time.LocalDate

data class Person(
        val id: String,
        val name: String,
        val birthday: LocalDate,
        val gender: Gender,
        override val acl: Acl?
) : AclObject