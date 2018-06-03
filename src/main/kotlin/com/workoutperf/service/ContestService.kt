package com.workoutperf.service

import com.workoutperf.model.AclPermission
import com.workoutperf.model.Contest
import com.workoutperf.model.Group
import com.workoutperf.model.Sid
import com.workoutperf.repository.ContestRepository
import com.workoutperf.repository.GroupRepository
import com.workoutperf.repository.PersonRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ContestService(
        val contestRepository: ContestRepository,
        val personRepository: PersonRepository,
        val groupRepository: GroupRepository
) {

    @Transactional
    fun addContest(contest: Contest): Optional<Contest> =
            if (!contestRepository.existsById(contest.id!!)) {
                val contestEntity = com.workoutperf.entity.Contest(contest)
                Optional.of(contestRepository.save(contestEntity).toModel())
            } else {
                Optional.empty()
            }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    fun updateContest(contest: Contest): Optional<Contest> =
            Optional.of(contestRepository.save(com.workoutperf.entity.Contest(contest)).toModel())

    @Transactional(readOnly = true)
    fun getContest(id: String): Optional<Contest> =
            contestRepository.findById(id).map { it.toModel() }

    @Transactional(readOnly = true)
    fun getAllContests(): List<Contest> =
            contestRepository.findAll().map { it.toModel() }.filter { it.checkAcl(Sid.current.get(), AclPermission.LIST) }

    @Transactional
    fun addMember(contestId: String, personId: String): Optional<Contest> {
        val contest = contestRepository.findById(contestId)
        return if (contest.isPresent && contest.get().members.none { it.id == personId }) {
            val person = personRepository.findById(personId)
            if (person.isPresent) {
                val contestEntity = contest.get()
                contestEntity.members.add(person.get())
                contestRepository.save(contestEntity)
                Optional.of(contestEntity.toModel())
            } else {
                Optional.empty()
            }
        } else {
            Optional.empty()
        }
    }

    @Transactional
    fun addDivision(contestId: String, division: Group): Optional<Group> {
        val contest = contestRepository.findById(contestId)
        return if (contest.isPresent && contest.get().divisions.none { it.name == division.name }) {
            val group = groupRepository.findById(division.id!!)
            if (!group.isPresent) {
                val groupEntity = groupRepository.save(com.workoutperf.entity.Group(division))
                contest.get().divisions.add(groupEntity)
                contestRepository.save(contest.get())
                Optional.of(groupEntity.toModel())
            } else {
                Optional.empty()
            }
        } else {
            Optional.empty()
        }
    }

    @Transactional
    fun addDivisionMember(contestId: String, divisionId: String, personId: String): Optional<Group> {
        val contest = contestRepository.findById(contestId)
        return if (contest.isPresent
                && contest.get().divisions.any { it.id == divisionId }
                && contest.get().divisions.first { it.id == divisionId }.members.none { it.id == personId }) {
            val person = personRepository.findById(personId)
            if (person.isPresent) {
                val contestEntity = contest.get()
                val divisionEntity = contestEntity.divisions.first { it.id == divisionId }
                divisionEntity.members.add(person.get())
                groupRepository.save(divisionEntity)
                Optional.of(divisionEntity.toModel())
            } else {
                Optional.empty()
            }
        } else {
            Optional.empty()
        }
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    fun removeContest(contestId: String): Boolean {
        val exists = contestRepository.existsById(contestId)
        if (exists) {
            contestRepository.deleteById(contestId)
        }
        return exists
    }
}