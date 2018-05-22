package com.workoutperf.repository

import com.workoutperf.entity.Group
import org.springframework.data.jpa.repository.JpaRepository

interface GroupRepository : JpaRepository<Group, String>