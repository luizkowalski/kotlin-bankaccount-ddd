package net.luizkowalski.accountsservice.domain.account

import com.fasterxml.jackson.annotation.JsonIgnore
import net.luizkowalski.accountsservice.domain.account.events.TransactionRegisteredEvent
import net.luizkowalski.accountsservice.domain.user.User
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.domain.AbstractAggregateRoot
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "accounts", indexes = arrayOf(
        Index(name = "idx_account_number", columnList = "number", unique = true),
        Index(name = "idx_account_iban", columnList = "iban", unique = true)
))
@EntityListeners(AuditingEntityListener::class)
data class Account(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @JsonIgnore
        var id: Long? = null,

        @CreatedDate
        var createdAt: Date? = null,

        @LastModifiedDate
        var updatedAt: Date? = null,

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

        @ElementCollection
        @CollectionTable(name = "transactions", joinColumns = arrayOf(JoinColumn(name = "account_id")))
        private var transactions: Set<Transaction> = emptySet(),

        @Version
        var version: Int = 0
) : AbstractAggregateRoot() {

    fun addTransaction(transaction: Transaction) {
        transactions = transactions.plus(transaction)
        registerEvent(TransactionRegisteredEvent(transaction, iban!!))
    }

    fun hasEnoughFunds(amount : Long): Boolean{
        return balance > amount
    }
}