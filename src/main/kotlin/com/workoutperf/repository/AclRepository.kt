package com.workoutperf.repository

import com.workoutperf.entity.Acl
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AclRepository : JpaRepository<Acl, Int>