package com.workoutperf.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class AclEntry(
        @Id
        val id: String? = null,
        val sid: String = "",
        val permission: Int = 0,
        @ManyToOne
        @JoinColumn(name = "acl_id")
        val acl: Acl? = null
) {
    constructor(aclEntry: com.workoutperf.model.AclEntry, acl: com.workoutperf.model.Acl) : this(
            id = "${acl.id}_${aclEntry.sid}",
            sid = aclEntry.sid,
            permission = aclEntry.permission
    )

    fun toModel() =
            com.workoutperf.model.AclEntry(
                    sid = this.sid,
                    permission = this.permission
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