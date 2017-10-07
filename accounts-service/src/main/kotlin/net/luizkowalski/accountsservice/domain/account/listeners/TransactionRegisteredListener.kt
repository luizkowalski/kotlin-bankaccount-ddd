package net.luizkowalski.accountsservice.domain.account.listeners

import net.luizkowalski.accountsservice.domain.account.events.TransactionRegisteredEvent
import net.luizkowalski.accountsservice.infrastructure.repositories.AccountsRepository
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class TransactionRegisteredListener(
        val accountsRepository: AccountsRepository
) {

    @EventListener
    fun transactionRegistered(event: TransactionRegisteredEvent) {
        var account = accountsRepository.findByIban(event.iban)!!
        account.balance += event.transaction.amount
    }
}