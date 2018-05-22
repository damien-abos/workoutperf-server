package com.workoutperf.repository

import com.workoutperf.entity.Contest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContestRepository : JpaRepository<Contest, String>