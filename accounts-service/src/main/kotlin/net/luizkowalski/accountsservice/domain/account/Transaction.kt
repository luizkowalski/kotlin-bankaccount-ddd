package net.luizkowalski.accountsservice.domain.account

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class Transaction(
        @Column(length = 1000)
        var amount: Long = 0L,

        @Column(nullable = false)
        var currency: String = "EUR",

        @Column(nullable = false)
        var iban: String? = null,

        @Column(nullable = false, length = 100)
        var transactionId: String? = null,

        @Column(nullable = false, length = 180)
        var reason: String = "",

        @Column(nullable = false)
        var flowId: String? = null
)