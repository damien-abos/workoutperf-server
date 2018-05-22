package com.workoutperf.model

interface AclObject {
    val acl: Acl

    fun checkAcl(sid: Sid, permission: Int): Boolean =
            !acl.aclEntries.any { aclEntry ->
                (aclEntry.sid == Sgroup.Everyone.sid || aclEntry.sid == sid.sid) && (aclEntry.permission and (permission or AclPermission.DISABLED) == (permission or AclPermission.DISABLED))
            } && acl.aclEntries.any { aclEntry ->
                (aclEntry.sid == Sgroup.Everyone.sid || aclEntry.sid == sid.sid) && (aclEntry.permission and permission == permission)
            }
}