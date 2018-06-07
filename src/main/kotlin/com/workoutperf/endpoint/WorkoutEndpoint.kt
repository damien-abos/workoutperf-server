package com.workoutperf.endpoint

import com.workoutperf.model.Period
import com.workoutperf.model.Workout
import com.workoutperf.model.WorkoutType
import com.workoutperf.service.WorkoutService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping(path = ["/contests/{contestId}/workouts"], produces = [(MediaType.APPLICATION_JSON_VALUE)])
class WorkoutEndpoint(
        val workoutService: WorkoutService
) {

    //
    //
    // POST /contests/{contestId}/workouts

    data class AddContestWorkoutBody(
            val id: String,
            val name: String,
            val description: String = "",
            val period: Period = Period(),
            val weight: Double = 1.0,
            val type: WorkoutType = WorkoutType.SCORE_ASC_TIME_ASC
    ) {
        fun toModel() = Workout(
                id = this.id,
                name = this.name,
                description = this.description,
                period = this.period,
                weight = this.weight,
                type = this.type
        )
    }

    @RequestMapping(method = [RequestMethod.POST])
    fun addContestWorkout(
            @PathVariable("contestId") contestId: String,
            @RequestBody addContestWorkoutBody: AddContestWorkoutBody
    ): ResponseEntity<Any> {
        val workout = workoutService.addWorkout(contestId, addContestWorkoutBody.toModel())
        return if (workout.isPresent) {
            val location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{workoutId}")
                    .buildAndExpand(workout.get().id).toUri()
            created(location).build()
        } else {
            badRequest().build()
        }
    }

    //
    //
    // GET /contests/{contestId}/workouts/{workoutId}

    @RequestMapping(path = ["/{workoutId}"], method = [RequestMethod.GET])
    fun getContestWorkout(
            @PathVariable("contestId") contestId: String,
            @PathVariable("workoutId") workoutId: String
    ): ResponseEntity<Any> {
        val workout = workoutService.getWorkout(contestId, workoutId)
        return if (workout.isPresent) {
            ok(workout.get())
        } else {
            notFound().build()
        }
    }

}