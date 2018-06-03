package com.workoutperf.entity

import javax.persistence.*

@Entity(name = "people_group")
data class Group(
        @Id
        val id: String? = null,
        val name: String = "",
        @OneToOne(cascade = [CascadeType.ALL])
        val acl: Acl? = null
) {
    @ManyToMany
    @JoinTable(name = "group_members",
            joinColumns = [JoinColumn(name = "group_id", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name = "person_id", referencedColumnName = "id")])
    var members: MutableSet<Person> = mutableSetOf()

    constructor(group: com.workoutperf.model.Group) :
            this(
                    id = group.id,
                    name = group.name,
                    acl = if (group.acl != null) Acl(group.acl) else null
            ) {
        this.members = group.members
                .map { person -> Person(person) }
                .toCollection(LinkedHashSet(group.members.size))
    }

    fun toModel() =
            com.workoutperf.model.Group(
                    id = this.id,
                    name = this.name,
                    members = this.members
                            .map { person -> person.toModel() }
                            .toCollection(LinkedHashSet(this.members.size)),
                    acl = this.acl?.toModel()
            )
}