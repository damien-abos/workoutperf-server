package com.workoutperf.service

import com.workoutperf.model.Person
import com.workoutperf.repository.PersonRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonService(
  val personRepository: PersonRepository
) {
  fun addPerson(person: Person) =
    if (!personRepository.existsById(person.id)) {
      val personEntity = com.workoutperf.entity.Person(person)
      Optional.of(personRepository.save(personEntity).toModel())
    } else {
      Optional.empty()
    }

  fun getAllPeople() =
    personRepository.findAll().map { it.toModel() }

  fun getPerson(personId: String) =
    personRepository.findById(personId).map { it.toModel() }

  fun updatePerson(person: Person): Optional<Person> {
    val personEntity = personRepository.findById(person.id)
    return if (personEntity.isPresent) {
      val updatedPersonEntity = com.workoutperf.entity.Person(person)
      Optional.of(personRepository.save(updatedPersonEntity).toModel())
    } else {
      Optional.empty()
    }
  }

}
