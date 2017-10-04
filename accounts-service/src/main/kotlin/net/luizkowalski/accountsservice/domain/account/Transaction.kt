package net.luizkowalski.accountsservice.domain.account

import com.fasterxml.jackson.annotation.JsonIgnore
import net.luizkowalski.accountsservice.domain.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "transactions", indexes = arrayOf(
        Index(name = "idx_on_tid", columnList = "transactionId", unique = true),
        Index(columnList = "flowId")
))
data class Transaction(
        @Column(length = 1000)
        var amount: Long = 0L,

        @Column(nullable = false)
        var currency: String = "EUR",

        @ManyToOne
        @JsonIgnore
        var account: Account? = null,

        @Column(nullable = false, length = 100)
        var transactionId: String? = null,

        @Column(nullable = false)
        var flowId: String? = null
) : BaseEntity()