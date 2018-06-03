package com.workoutperf.endpoint

import com.workoutperf.service.TransactionService
import org.springframework.http.MediaType
import org.springframework.transaction.support.DefaultTransactionDefinition
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/tx"], produces = [(MediaType.APPLICATION_JSON_VALUE)])
class TransactionEndpoint(
        val transactionService: TransactionService
) {
    @RequestMapping(method = [RequestMethod.POST])
    fun begin() = transactionService.getTransaction(DefaultTransactionDefinition())

    @RequestMapping(path = ["/{txId}"], method = [RequestMethod.POST])
    fun commit(@PathVariable("txId") txId: String) = transactionService.commit(txId)

    @RequestMapping(path = ["/{txId}"], method = [RequestMethod.DELETE])
    fun rollback(@PathVariable("txId") txId: String) = transactionService.rollback(txId)
}