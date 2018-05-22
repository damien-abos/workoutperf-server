package com.workoutperf.endpoint

import com.workoutperf.model.Acl
import com.workoutperf.model.Gender
import com.workoutperf.model.Person
import com.workoutperf.service.PersonService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.notFound
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.time.LocalDate

@RestController
@RequestMapping(path = ["/people"], produces = [(MediaType.APPLICATION_JSON_VALUE)])
class PersonEndpoint(val personService: PersonService) {

    //
    //
    // POST /people

    data class AddPersonBody(
            val id: String,
            val name: String,
            val birthday: LocalDate,
            val gender: Gender
    ) {
        fun toModel() = Person(
                id = this.id,
                name = this.name,
                birthday = this.birthday,
                gender = this.gender,
                acl = Acl()
        )
    }

    @RequestMapping(method = [RequestMethod.POST])
    fun addPerson(
            @RequestBody addPersonBody: AddPersonBody
    ): ResponseEntity<Any> {
        val person = personService.addPerson(addPersonBody.toModel())
        return if (person.isPresent) {
            val location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{contestId}")
                    .buildAndExpand(person.get().id).toUri()
            ResponseEntity.created(location).build()
        } else {
            ResponseEntity.badRequest().build()
        }
    }

    //
    //
    // GET /people

    @RequestMapping(method = [RequestMethod.GET])
    fun getAllPeople() =
            personService.getAllPeople()

    //
    //
    // PATCH /people/{personId}

    data class MergePersonBody(
            val name: String? = null,
            val birthday: LocalDate? = null,
            val gender: Gender? = null
    ) {
        fun merge(person: Person): Person {
            var name = person.name
            if (this.name != null) {
                name = this.name
            }
            var birthday = person.birthday
            if (this.birthday != null) {
                birthday = this.birthday
            }
            var gender = person.gender
            if (this.gender != null) {
                gender = this.gender
            }
            return Person(
                    id = person.id,
                    name = name,
                    birthday = birthday,
                    gender = gender,
                    acl = person.acl
            )
        }
    }

    @RequestMapping(path = ["/{personId}"], method = [RequestMethod.PATCH])
    fun mergePerson(
            @PathVariable("personId") personId: String,
            @RequestBody mergePersonBody: MergePersonBody
    ): ResponseEntity<Any> {
        val person = personService.getPerson(personId)
        return if (person.isPresent) {
            ok(personService.updatePerson(mergePersonBody.merge(person.get())))
        } else {
            notFound().build()
        }
    }

}