package com.workoutperf.config

import com.atomikos.icatch.jta.UserTransactionImp
import com.atomikos.icatch.jta.UserTransactionManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.jta.JtaTransactionManager
import javax.transaction.TransactionManager
import javax.transaction.UserTransaction


@Configuration
class JtaConfig {

    @Bean
    fun userTransaction(): UserTransaction {
        val userTransactionImp = UserTransactionImp()
        userTransactionImp.setTransactionTimeout(10000)
        return userTransactionImp
    }

    @Bean(name = ["atomikosTransactionManager"], initMethod = "init", destroyMethod = "close")
    fun atomikosTransactionManager(userTransaction: UserTransaction): TransactionManager {
        val userTransactionManager = UserTransactionManager()
        userTransactionManager.forceShutdown = false

        AtomikosJtaPlatform.transactionManager = userTransactionManager

        return userTransactionManager
    }

    @Bean
    @DependsOn("userTransaction", "atomikosTransactionManager")
    fun transactionManager(userTransaction: UserTransaction, atomikosTransactionManager: TransactionManager): PlatformTransactionManager {
        AtomikosJtaPlatform.transaction = userTransaction
        return JtaTransactionManager(userTransaction, atomikosTransactionManager)
    }

}