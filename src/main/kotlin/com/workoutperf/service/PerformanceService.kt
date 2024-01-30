package com.workoutperf.service

import com.workoutperf.model.Performance
import com.workoutperf.repository.ContestRepository
import com.workoutperf.repository.PerformanceRepository
import com.workoutperf.repository.WorkoutRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class PerformanceService(
  val performanceRepository: PerformanceRepository,
  val contestRepository: ContestRepository,
  val workoutRepository: WorkoutRepository
) {

  fun addPerformance(contestId: String, workoutId: String, performance: Performance): Optional<Performance> {
    val contest = contestRepository.findById(contestId)
    val workout = workoutRepository.findById(workoutId)
    return if (contest.isPresent
      && workout.isPresent
      && contest.get().id == workout.get().contest!!.id) {
      val performanceEntity = com.workoutperf.entity.Performance(performance)
      performanceEntity.workout = workout.get()
      performanceRepository.save(performanceEntity)
      Optional.of(performanceEntity.toModel())
    } else {
      Optional.empty()
    }
  }


}
