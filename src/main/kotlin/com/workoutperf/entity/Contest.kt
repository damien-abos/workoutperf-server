package com.workoutperf.entity

import javax.persistence.*

@Entity
data class Contest(
        @Id
        val id: String? = null,
        val name: String = "",
        val description: String = "",
        val period: Period = Period()
) {
    @OneToMany(mappedBy = "contest")
    val workouts: MutableSet<Workout> = mutableSetOf()

    @ManyToMany
    @JoinTable(name = "contest_organizers",
            joinColumns = [JoinColumn(name = "contest_id", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name = "person_id", referencedColumnName = "id")])
    val organizers: MutableSet<Person> = mutableSetOf()

    @ManyToMany
    @JoinTable(name = "contest_judges",
            joinColumns = [JoinColumn(name = "contest_id", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name = "person_id", referencedColumnName = "id")])
    val judges: MutableSet<Person> = mutableSetOf()

    @ManyToMany
    @JoinTable(name = "contest_members",
            joinColumns = [JoinColumn(name = "contest_id", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name = "person_id", referencedColumnName = "id")])
    val members: MutableSet<Person> = mutableSetOf()

    @ManyToMany
    @JoinTable(name = "contest_divisions",
            joinColumns = [JoinColumn(name = "contest_id", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name = "division_id", referencedColumnName = "id")])
    val divisions: MutableSet<Group> = mutableSetOf()

    constructor(contest: com.workoutperf.model.Contest) : this(
            id = contest.id,
            name = contest.name,
            description = contest.description,
            period = com.workoutperf.entity.Period(
                    begin = contest.period.begin,
                    end = contest.period.end
            )
    )

    fun toModel() =
            com.workoutperf.model.Contest(
                    id = this.id,
                    name = this.name,
                    description = this.description,
                    period = com.workoutperf.model.Period(this.period.begin, this.period.end),
                    workouts = this.workouts.map { workout -> workout.toModel() }.toCollection(LinkedHashSet(this.workouts.size)),
                    organizers = this.organizers.map { person -> person.toModel() }.toCollection(LinkedHashSet(this.organizers.size)),
                    judges = this.judges.map { person -> person.toModel() }.toCollection(LinkedHashSet(this.judges.size)),
                    members = this.members.map { person -> person.toModel() }.toCollection(LinkedHashSet(this.members.size)),
                    divisions = this.divisions.map { division -> division.toModel() }.toCollection(LinkedHashSet(this.divisions.size))
            )
}
