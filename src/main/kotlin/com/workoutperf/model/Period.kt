package com.workoutperf.model

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class Period(
  val begin: LocalDateTime = LocalDateTime.of(LocalDate.of(2000, 1, 1), LocalTime.MIN),
  val end: LocalDateTime = LocalDateTime.of(LocalDate.of(2100, 12, 31), LocalTime.MAX)
)
