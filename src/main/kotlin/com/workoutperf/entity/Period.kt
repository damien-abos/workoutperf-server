package com.workoutperf.entity

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class Period(
  @Column(name = "begin_period")
  val begin: LocalDateTime = LocalDateTime.of(LocalDate.of(2000, 1, 1), LocalTime.MIN),
  @Column(name = "end_period")
  val end: LocalDateTime = LocalDateTime.of(LocalDate.of(2100, 12, 31), LocalTime.MAX)
) {
  constructor(period: com.workoutperf.model.Period) :
    this(
      begin = period.begin,
      end = period.end
    )

  fun toModel() =
    com.workoutperf.model.Period(
      begin = this.begin,
      end = this.end
    )
}
