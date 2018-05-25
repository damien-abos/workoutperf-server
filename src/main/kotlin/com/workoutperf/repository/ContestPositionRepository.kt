package com.workoutperf.repository

import com.workoutperf.entity.ContestPosition
import org.springframework.data.jpa.repository.JpaRepository

interface ContestPositionRepository : JpaRepository<ContestPosition, String>