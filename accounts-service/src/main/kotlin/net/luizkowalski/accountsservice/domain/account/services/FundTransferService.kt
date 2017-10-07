package net.luizkowalski.accountsservice.domain.account.services

import net.luizkowalski.accountsservice.domain.account.Transaction
import net.luizkowalski.accountsservice.infrastructure.exceptions.AccountNotFoundException
import net.luizkowalski.accountsservice.infrastructure.exceptions.NotEnoughFunds
import net.luizkowalski.accountsservice.infrastructure.repositories.AccountsRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class FundTransferService(val accountsRepository: AccountsRepository) {

    @Transactional
    fun transfer(origin: String, destination: String, amount: Long, reason: String) {
        var originAccount = accountsRepository.findByIban(origin) ?: throw AccountNotFoundException(origin)
        var destinationAccount = accountsRepository.findByIban(destination) ?: throw AccountNotFoundException(destination)

        if (!originAccount.hasEnoughFunds(amount))
            throw NotEnoughFunds()

        var flowId = UUID.randomUUID().toString()

        originAccount.addTransaction(createTransaction(destination, amount * -1, reason, flowId))
        destinationAccount.addTransaction(createTransaction(origin, amount, reason, flowId))

        accountsRepository.save(originAccount)
        accountsRepository.save(destinationAccount)
    }

    private fun createTransaction(iban: String, amount: Long, reason: String, flowId: String): Transaction {
        return Transaction(
                amount,
                "EUR",
                iban,
                UUID.randomUUID().toString(),
                reason,
                flowId)
    }
}