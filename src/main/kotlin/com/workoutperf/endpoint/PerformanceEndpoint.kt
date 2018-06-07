package com.workoutperf.endpoint

import com.workoutperf.model.Performance
import com.workoutperf.model.Person
import com.workoutperf.service.PerformanceService
import com.workoutperf.service.PersonService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.time.Duration
import java.time.LocalDateTime

@RestController
@RequestMapping(path = ["/contests/{contestId}/workouts/{workoutId}/performances"], produces = [(MediaType.APPLICATION_JSON_VALUE)])
class PerformanceEndpoint(
        val personService: PersonService,
        val performanceService: PerformanceService
) {

    //
    //
    // POST /contests/{contestId}/workouts/{workoutId}/performances

    data class AddPerformanceBody(
            val score: Int = 0,
            val duration: Duration = Duration.ZERO,
            val date: LocalDateTime = LocalDateTime.now(),
            //val location: Location,
            val comment: String = "",
            val athlete: String,
            val judge: String?
    ) {
        fun toModel(id: String, athlete: Person, judge: Person?) = Performance(
                id = id,
                score = this.score,
                duration = this.duration,
                date = this.date,
                //location = this.location,
                comment = this.comment,
                athlete = athlete,
                judge = judge
        )
    }

    @RequestMapping(method = [RequestMethod.POST])
    fun addContestWorkoutPerformance(
            @PathVariable("contestId") contestId: String,
            @PathVariable("workoutId") workoutId: String,
            @RequestBody addPerformanceBody: AddPerformanceBody
    ): ResponseEntity<Any> {
        val athlete = personService.getPerson(addPerformanceBody.athlete)
        val judge: Person? = if (addPerformanceBody.judge != null) personService.getPerson(addPerformanceBody.judge).get() else null
        val performance = performanceService.addPerformance(contestId, workoutId, addPerformanceBody.toModel("${contestId}_${workoutId}_${athlete.get().id}", athlete.get(), judge))
        return if (performance.isPresent) {
            val location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{performanceId}")
                    .buildAndExpand(performance.get().id).toUri()
            ResponseEntity.created(location).build()
        } else {
            ResponseEntity.badRequest().build()
        }
    }

    //
    //
    // GET /contests/{contestId}/workouts/{workoutId}/performances

    @RequestMapping(method = [RequestMethod.GET])
    fun getAllContestWorkoutPerformances(
            @PathVariable("contestId") contestId: String,
            @PathVariable("workoutId") workoutId: String
    ): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build()
    }

}