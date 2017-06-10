package net.luizkowalski.accountsservice.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "accounts", indexes = arrayOf(
        Index(name = "idx_account_number", columnList = "number", unique = true),
        Index(name = "idx_account_iban", columnList = "iban", unique = true)
))
data class Account(
        @ManyToOne
        @JsonIgnore
        var user: User? = null,

        @Column(nullable = false)
        var number: String? = null,

        @Column
        var accountType: AccountType = AccountType.CHECKING,

        @Column(nullable = false, length = 100)
        var iban: String? = null,

        @Column(length = 1000)
        var balance: Long = 1000L,

        @OneToMany(mappedBy = "account")
        var transactions: List<Transaction> = emptyList(),

        @Version
        var version: Int = 0
) : BaseEntity()