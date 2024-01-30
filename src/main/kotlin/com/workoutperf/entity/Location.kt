package com.workoutperf.entity

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Location(
  @Id
  val id: String? = null,
  val name: String = "",
  val address: String? = null,
  val longitude: Double? = null,
  val latitude: Double? = null
) {
  constructor(location: com.workoutperf.model.Location) :
    this(
      id = location.id,
      name = location.name,
      address = location.address,
      longitude = location.longitude,
      latitude = location.latitude
    )

  fun toModel() =
    com.workoutperf.model.Location(
      id = this.id,
      name = this.name,
      address = this.address,
      longitude = this.longitude,
      latitude = this.latitude
    )
}
