package com.workoutperf.entity

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
data class AclClass(
        @Id
        val collection: String? = null,
        @OneToOne(cascade = [CascadeType.ALL])
        val acl: Acl? = null
) {

    constructor(aclClass: com.workoutperf.model.AclClass) :
            this(
                    collection = aclClass.collection,
                    acl = if (aclClass.acl != null) Acl(aclClass.acl) else null
            )



    fun toModel() =
            com.workoutperf.model.AclClass(
                    collection = this.collection,
                    acl = this.acl?.toModel()
            )
}