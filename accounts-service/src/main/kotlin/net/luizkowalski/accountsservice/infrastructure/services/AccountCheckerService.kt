package net.luizkowalski.accountsservice.infrastructure.services

import org.apache.commons.logging.Log
import org.apache.commons.logging.impl.SLF4JLogFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class AccountCheckerService(
        val logger: Log = SLF4JLogFactory.getFactory().getInstance(AccountCheckerService::class.java)!!) {

    @Scheduled(fixedDelay = 5000L)
    fun checkAccounts() {
        logger.info("I should be doing something useful but I'm just acting as a placeholder now")
    }
}