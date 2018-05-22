package com.workoutperf.entity

import javax.persistence.*

@Entity
data class AclEntry(
        @Id
        @GeneratedValue
        val id: Int? = null,
        val sid: String = "",
        val permission: Int = 0,
        @ManyToOne
        @JoinColumn(name = "acl_id")
        val acl: Acl = Acl()
) {
    constructor(aclEntry: com.workoutperf.model.AclEntry) : this(
            id = null,
            sid = aclEntry.sid,
            permission = aclEntry.permission
    )

    fun toModel() =
            com.workoutperf.model.AclEntry(
                    sid = this.sid,
                    permission = this.permission
            )
}