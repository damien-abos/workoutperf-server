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
        var acl: Acl? =null
) {

    constructor(person: com.workoutperf.model.Person) :
            this(
                    id = person.id,
                    name = person.name,
                    birthday = person.birthday,
                    gender = person.gender.name,
                    acl = if (person.acl != null) Acl(person.acl) else null
            )

    fun toModel(): com.workoutperf.model.Person =
            com.workoutperf.model.Person(
                    id = this.id!!,
                    name = this.name,
                    birthday = this.birthday,
                    gender = com.workoutperf.model.Gender.valueOf(this.gender),
                    acl = this.acl?.toModel()
            )

    override fun hashCode(): Int {
        return if (this.id != null) this.id.hashCode() else 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as Person?
        return id == that!!.id
    }

}