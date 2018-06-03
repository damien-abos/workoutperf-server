package com.workoutperf.entity

import javax.persistence.*

@Entity
data class Acl(
        @Id
        val id: String? = null,
        @OneToMany(mappedBy = "acl")
        val aclEntries: MutableSet<AclEntry> = mutableSetOf()
) {
    constructor(acl: com.workoutperf.model.Acl) : this(
            id = acl.id,
            aclEntries = acl.aclEntries
                    .map { aclEntry -> AclEntry(aclEntry, acl) }
                    .toCollection(LinkedHashSet(acl.aclEntries.size))
    )

    fun toModel() =
            com.workoutperf.model.Acl(
                    id = this.id,
                    aclEntries = this.aclEntries
                            .map { aclEntry -> aclEntry.toModel() }
                            .toCollection(LinkedHashSet(this.aclEntries.size))
            )

    override fun hashCode(): Int {
        return if (this.id != null) this.id.hashCode() else 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as Acl
        return id == that.id
    }

}