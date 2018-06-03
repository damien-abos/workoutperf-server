package com.workoutperf.service

import org.springframework.stereotype.Service
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.TransactionDefinition
import org.springframework.transaction.TransactionStatus
import java.util.*

@Service
class TransactionService(
        val transactionManager: PlatformTransactionManager
) {
    private val transactionStatusById : MutableMap<String, TransactionStatus> = mutableMapOf()

    fun getTransaction(definition: TransactionDefinition?): String {
        val transactionStatus = transactionManager.getTransaction(definition)
        val uuid = UUID.randomUUID().toString()
        transactionStatusById[uuid] = transactionStatus
        return uuid
    }

    fun rollback(uuid: String) {
        val status = transactionStatusById[uuid]
        if (status != null) {
            transactionStatusById.remove(uuid)
            transactionManager.rollback(status)
        }
    }

    fun commit(uuid: String) {
        val status = transactionStatusById[uuid]
        if (status != null) {
            transactionStatusById.remove(uuid)
            transactionManager.commit(status)
        }
    }
}