package com.workoutperf.model

class AclPermission {
    companion object {
        val READ: Int = 0x00000001
        val WRITE: Int = 0x00000002
        val DELETE: Int = 0x00000004
        val LIST: Int = 0x00000008
        val ACL: Int = 0x00000010
        val FULL: Int = 0x20000000
        val DISABLED: Int = 0x40000000
    }
}
