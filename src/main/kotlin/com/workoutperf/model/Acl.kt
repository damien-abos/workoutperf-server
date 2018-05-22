package com.workoutperf.model

data class Acl(
        val aclEntries: MutableSet<AclEntry> = mutableSetOf()
)