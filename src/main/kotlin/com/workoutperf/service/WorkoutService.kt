package com.workoutperf.service

import com.workoutperf.model.Workout
import com.workoutperf.repository.ContestRepository
import com.workoutperf.repository.WorkoutRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class WorkoutService(
        val contestRepository: ContestRepository,
        val workoutRepository: WorkoutRepository
) {

    fun addWorkout(contestId: String, workout: Workout): Optional<Workout> {
        val contest = contestRepository.findById(contestId)
        return if (contest.isPresent && contest.get().workouts.none { it.id == workout.id }) {
            val workoutEntity = com.workoutperf.entity.Workout(workout)
            workoutEntity.contest = contest.get()
            workoutRepository.save(workoutEntity)
            Optional.of(workoutEntity.toModel())
        } else {
            Optional.empty()
        }
    }

    fun getWorkout(contestId: String, workoutId: String): Optional<Workout> {
        val workout = workoutRepository.findById(workoutId)
        return if (workout.isPresent && workout.get().contest!!.id == contestId) {
            Optional.of(workout.get().toModel())
        } else {
            Optional.empty()
        }
    }

}