package com.workoutperf.entity

import java.time.Duration
import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
data class Performance(
        @Id
        val id: String? = null,
        val score: Int = 0,
        val duration: Duration = Duration.ZERO,
        val date: LocalDateTime = LocalDateTime.now(),
        //@OneToOne
        //val location: Location,
        val comment: String = "",
        @OneToOne
        var athlete: Person? = null,
        @OneToOne
        var judge: Person? = null,
        @OneToOne
        var workout: Workout? = null
) {
    constructor(performance: com.workoutperf.model.Performance):
            this(
                    id = performance.id,
                    score = performance.score,
                    duration = performance.duration,
                    date = performance.date,
                    //location = Location(performance.location),
                    comment = performance.comment,
                    athlete = Person(performance.athlete),
                    judge = if (performance.judge != null) Person(performance.judge) else null,
                    workout = if (performance.workout != null) Workout(performance.workout) else null
            )

    fun toModel()  =
            com.workoutperf.model.Performance(
                    id = this.id,
                    score = this.score,
                    duration = this.duration,
                    date = this.date,
                    //location = this.location.toModel(),
                    comment = this.comment,
                    athlete = this.athlete!!.toModel(),
                    judge = this.judge?.toModel(),
                    workout = this.workout!!.toModel()
            )
}