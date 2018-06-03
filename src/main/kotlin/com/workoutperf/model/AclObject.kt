package com.workoutperf.model

interface AclObject {
    val acl: Acl?

    fun checkAcl(sid: Sid, permission: Int): Boolean =
            acl != null && acl!!.aclEntries.none { aclEntry ->
                (aclEntry.sid == Sid.Everyone.sid || aclEntry.sid == sid.sid) && (aclEntry.permission and (permission or AclPermission.DISABLED) == (permission or AclPermission.DISABLED))
            } && acl!!.aclEntries.any { aclEntry ->
                (aclEntry.sid == Sid.Everyone.sid || aclEntry.sid == sid.sid) && (aclEntry.permission and permission == permission)
            }
}