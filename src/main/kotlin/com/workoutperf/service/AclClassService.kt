package com.workoutperf.service

import com.workoutperf.model.AclClass
import com.workoutperf.repository.AclClassRepository
import org.springframework.stereotype.Service

@Service
class AclClassService(
        val aclClassRepository: AclClassRepository
) {

    val aclClassByName: MutableMap<String, AclClass> = mutableMapOf()

    fun getAclClass(name: String) : AclClass {
        var aclClass = aclClassByName[name]
        if (aclClass == null) {
            val aclClassClass = aclClassRepository.findById("Class").get().toModel()
            aclClass = aclClassRepository.findById(name).get().toModel()
            aclClassClass.acl?.aclEntries?.forEach { aclClass.acl?.aclEntries?.add(it) }
            aclClassByName[name] = aclClass
        }
        return aclClass
    }
}