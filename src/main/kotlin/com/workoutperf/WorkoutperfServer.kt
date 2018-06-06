package com.workoutperf

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WorkoutperfServer {

    fun main(args: Array<String>) {
        runApplication<WorkoutperfServer>(*args)
    }
}
