package com.workoutperf.repository

import com.workoutperf.entity.AclClass
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AclClassRepository : JpaRepository<AclClass, String>
