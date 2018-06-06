package com.workoutperf.model

data class Acl(
        val id: String?,
        val aclEntries: MutableSet<AclEntry> = mutableSetOf()
) {
    override fun toString() = "Acl(id=${id})"
}