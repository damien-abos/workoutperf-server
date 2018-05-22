package com.workoutperf.entity

import java.time.LocalDate
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
data class Person(
        @Id
        val id: String? = null,
        val name: String = "",
        val birthday: LocalDate = LocalDate.now(),
        val gender: String = "MALE",
        @OneToOne(cascade = [CascadeType.ALL])
        var acl: Acl = Acl()
) {

    constructor(person: com.workoutperf.model.Person) :
            this(
                    id = person.id,
                    name = person.name,
                    birthday = person.birthday,
                    gender = person.gender.name,
                    acl = Acl(person.acl)
            )

    fun toModel(): com.workoutperf.model.Person =
            com.workoutperf.model.Person(
                    id = this.id!!,
                    name = this.name,
                    birthday = this.birthday,
                    gender = com.workoutperf.model.Gender.valueOf(this.gender),
                    acl = this.acl.toModel()
            )
}