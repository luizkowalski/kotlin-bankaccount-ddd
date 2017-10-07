package net.luizkowalski.accountsservice.domain.account.events

import net.luizkowalski.accountsservice.domain.account.Transaction

data class TransactionRegisteredEvent(
        var transaction: Transaction,
        var iban: String
)