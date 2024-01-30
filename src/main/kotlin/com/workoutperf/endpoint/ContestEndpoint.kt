package com.workoutperf.endpoint

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.workoutperf.model.Contest
import com.workoutperf.model.Group
import com.workoutperf.model.Period
import com.workoutperf.service.ContestService
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder


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
      divisions = mutableSetOf()
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

  data class GetContest(
    val id: String?,
    val name: String,
    val description: String,
    val workouts: List<String>,
    val divisions: List<String>,
    val period: Period,
    val organizers: List<String>,
    val judges: List<String>,
    val members: List<String>
  ) {
    constructor(contest: Contest) : this(
      id = contest.id,
      name = contest.name,
      description = contest.description,
      workouts = contest.workouts.map { it.id!! },
      divisions = contest.divisions.map { it.id!! },
      period = contest.period,
      organizers = contest.organizers.map { it.id },
      judges = contest.judges.map { it.id },
      members = contest.members.map { it.id }
    )
  }

  @RequestMapping(path = ["/{contestId}"], method = [RequestMethod.GET])
  fun getContest(
    @PathVariable("contestId") contestId: String
  ): ResponseEntity<Any> {
    val contest = contestService.getContest(contestId)
    return if (contest.isPresent) {
      ok(GetContest(contest.get()))
    } else {
      notFound().build()
    }
  }

  //
  //
  // PUT /contests/{contestId}

  data class UpdateContestBody(
    val name: String? = null,
    val description: String? = null,
    val period: Period? = null
  ) {

    fun merge(contest: Contest) = Contest(
      id = contest.id,
      name = if (this.name != null) this.name else contest.name,
      description = if (this.description != null) this.description else contest.description,
      period = if (this.period != null) this.period else contest.period,
      workouts = contest.workouts,
      organizers = contest.organizers,
      judges = contest.judges,
      members = contest.members,
      divisions = contest.divisions
    )
  }

  @RequestMapping(path = ["/{contestId}"], method = [RequestMethod.PUT])
  fun updateContest(
    @PathVariable("contestId") contestId: String,
    @RequestBody updateContestBody: UpdateContestBody
  ): ResponseEntity<Any> {
    val contest = contestService.getContest(contestId)
    return if (contest.isPresent) {
      val updatedContest = contestService.updateContest(updateContestBody.merge(contest.get()))
      ok(GetContest(updatedContest.get()))
    } else {
      notFound().build()
    }
  }

  //
  //
  // DELETE /contests/{contestId}

  @RequestMapping(path = ["/{contestId}"], method = [RequestMethod.DELETE])
  fun removeContest(@PathVariable("contestId") contestId: String): ResponseEntity<Any> {
    return if (contestService.removeContest(contestId)) {
      noContent().build()
    } else {
      notFound().build()
    }
  }

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
    fun toModel(contestId: String) = Group(
      id = "${contestId}_${this.name}",
      name = this.name,
      members = mutableSetOf()
    )
  }

  @RequestMapping(path = ["/{contestId}/divisions"], method = [RequestMethod.POST])
  fun addContestDivision(
    @PathVariable("contestId") contestId: String,
    @RequestBody addContestDivisionBody: AddContestDivisionBody
  ): ResponseEntity<Any> {
    val division = contestService.addDivision(contestId, addContestDivisionBody.toModel(contestId))
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
  //

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
