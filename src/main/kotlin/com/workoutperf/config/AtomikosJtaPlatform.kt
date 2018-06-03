package com.workoutperf.config

import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform
import javax.transaction.TransactionManager
import javax.transaction.UserTransaction

class AtomikosJtaPlatform : AbstractJtaPlatform() {
    override fun locateTransactionManager() = transactionManager

    override fun locateUserTransaction() = transaction

    companion object {
        var transactionManager: TransactionManager? = null
        var transaction: UserTransaction? = null
    }

}