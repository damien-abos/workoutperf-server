package com.workoutperf.entity

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
data class AclClass(
        @Id
        val collection: String,
        @OneToOne(cascade = [CascadeType.ALL])
        val acl: Acl
) {
    constructor(aclClass: com.workoutperf.model.AclClass) :
            this(
                    collection = aclClass.collection,
                    acl = Acl(aclClass.acl)
            )

    fun toModel() =
            com.workoutperf.model.AclClass(
                    collection = this.collection,
                    acl = this.acl.toModel()
            )
}