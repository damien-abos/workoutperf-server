package com.workoutperf.endpoint

import com.workoutperf.model.Acl
import com.workoutperf.model.Contest
import com.workoutperf.model.Group
import com.workoutperf.model.Period
import com.workoutperf.service.ContestService
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*


@RestController
@RequestMapping(path = ["/contests"], produces = [APPLICATION_JSON_VALUE])
class ContestEndpoint(val contestService: ContestService) {


    //
    //
    // POST /contest

    data class AddContestBody(
            val id: String,
            val name: String,
            val description: String = "",
            val period: Period = Period()
    ) {

        fun toModel() = Contest(
                id = this.id,
                name = this.name,
                description = this.description,
                period = this.period,
                workouts = mutableSetOf(),
                organizers = mutableSetOf(),
                judges = mutableSetOf(),
                members = mutableSetOf(),
                divisions = mutableSetOf(),
                acl = Acl()
        )
    }

    @RequestMapping(method = [RequestMethod.POST])
    fun addContest(
            @RequestBody addContestBody: AddContestBody
    ): ResponseEntity<Any> {
        val contest = contestService.addContest(addContestBody.toModel())
        return if (contest.isPresent) {
            val location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{contestId}")
                    .buildAndExpand(contest.get().id).toUri()
            created(location).build()
        } else {
            badRequest().build()
        }
    }


    //
    //
    // GET /contests

    data class GetAllContest(
            val id: String?,
            val name: String,
            val description: String,
            val workouts: List<String>,
            val divisions: List<String>
    ) {
        constructor(contest: Contest) : this(
                id = contest.id,
                name = contest.name,
                description = contest.description,
                workouts = contest.workouts.map { it.id!! },
                divisions = contest.divisions.map { it.id!! }
        )
    }

    @RequestMapping(method = [RequestMethod.GET])
    fun getAllContests() =
            contestService.getAllContests().map { GetAllContest(it) }

    //
    //
    // GET /contests/{contestId}

    @RequestMapping(path = ["/{contestId}"], method = [RequestMethod.GET])
    fun getContest(
            @PathVariable("contestId") contestId: String
    ): ResponseEntity<Any> {
        val contest = contestService.getContest(contestId)
        return if (contest.isPresent) {
            ok(contest)
        } else {
            notFound().build()
        }
    }

    //
    //
    // TODO('PUT /contests/{contestId}')

    //
    //
    // POST /contests/{contestId}/members

    data class AddContestPersonBody(
            val id: String
    ) {
        fun toModel(): String = this.id
    }

    @RequestMapping(path = ["/{contestId}/members"], method = [RequestMethod.POST])
    fun addContestMember(
            @PathVariable("contestId") contestId: String,
            @RequestBody addContestPersonBody: AddContestPersonBody
    ): ResponseEntity<Any> {
        val person = contestService.addMember(contestId, addContestPersonBody.toModel())
        return if (person.isPresent) {
            val location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{personId}")
                    .buildAndExpand(person.get().id).toUri()
            created(location).build()
        } else {
            badRequest().build()
        }
    }

    //
    //
    // POST /contests/{contestId}/organizers

    //
    //
    // POST /contests/{contestId}/judges

    //
    //
    // POST /contest/{contestId}/divisions

    data class AddContestDivisionBody(
            val name: String
    ) {
        fun toModel() = Group(
                id = UUID.randomUUID().toString(),
                name = this.name,
                members = mutableSetOf(),
                acl = Acl()
        )
    }

    @RequestMapping(path = ["/{contestId}/divisions"], method = [RequestMethod.POST])
    fun addContestDivision(
            @PathVariable("contestId") contestId: String,
            @RequestBody addContestDivisionBody: AddContestDivisionBody
    ): ResponseEntity<Any> {
        val division = contestService.addDivision(contestId, addContestDivisionBody.toModel())
        return if (division.isPresent) {
            val location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{divisionId}")
                    .buildAndExpand(division.get().id).toUri()
            created(location).build()
        } else {
            badRequest().build()
        }
    }

    //
    //
    // POST /contest/{contestId}/divisions/{divisionId}/members

    @RequestMapping(path = ["/{contestId}/divisions/{divisionId}/members"], method = [RequestMethod.POST])
    fun addContestDivisionMember(
            @PathVariable("contestId") contestId: String,
            @PathVariable("divisionId") divisionId: String,
            @RequestBody addContestPersonBody: AddContestPersonBody
    ): ResponseEntity<Any> {
        val group = contestService.addDivisionMember(contestId, divisionId, addContestPersonBody.toModel())
        return if (group.isPresent) {
            val location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{personId}")
                    .buildAndExpand(addContestPersonBody.id).toUri()
            created(location).build()
        } else {
            badRequest().build()
        }
    }

}