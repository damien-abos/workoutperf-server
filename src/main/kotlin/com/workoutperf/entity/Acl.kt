package com.workoutperf.entity

import javax.persistence.*

@Entity
data class Acl(
        @Id
        @GeneratedValue
        @SequenceGenerator(name = "acl_id_seq")
        val id: Int? = null,
        @OneToMany(mappedBy = "acl")
        val aclEntries: MutableSet<AclEntry> = mutableSetOf()
) {
    constructor(acl: com.workoutperf.model.Acl) : this(
            id = null,
            aclEntries = acl.aclEntries
                    .map { aclEntry -> AclEntry(aclEntry) }
                    .toCollection(LinkedHashSet(acl.aclEntries.size))
    )

    fun toModel() =
            com.workoutperf.model.Acl(
                    aclEntries = this.aclEntries
                            .map { aclEntry -> aclEntry.toModel() }
                            .toCollection(LinkedHashSet(this.aclEntries.size))
            )
}