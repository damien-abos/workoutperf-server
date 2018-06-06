package com.workoutperf.model

data class AclClass(
        val collection: String?,
        val acl: Acl?
) {
    fun <T : AclObject> apply(obj: T) {
        this.acl?.aclEntries?.map { aclEntry ->
            when (aclEntry.sid) {
                Sid.Everyone.sid -> AclEntry(aclEntry.sid, aclEntry.permission)
                Sid.Owner.sid -> AclEntry(Sid.current.get().sid, aclEntry.permission)
                else -> AclEntry(aclEntry.sid, aclEntry.permission)
            }
        }?.forEach {
            obj.acl?.aclEntries?.add(it)
        }
    }
}