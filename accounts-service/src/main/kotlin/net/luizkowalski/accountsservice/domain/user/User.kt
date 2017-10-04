package net.luizkowalski.accountsservice.domain.user

import net.luizkowalski.accountsservice.domain.BaseEntity
import net.luizkowalski.accountsservice.domain.account.Account
import javax.persistence.*

@Entity
@Table(name = "users", indexes = arrayOf(
        Index(name = "idx_email_on_user", columnList = "email", unique = true),
        Index(name = "idx_passport_number_on_user", columnList = "passportNumber", unique = true)
)
)
data class User(
        @Column(nullable = false, length = 200)
        var name: String = "",

        @Column(nullable = false, length = 50)
        var passportNumber: String? = null,

        @Column(nullable = true, length = 100)
        var email: String? = null,

        @OneToMany(mappedBy = "user", cascade = arrayOf(CascadeType.ALL))
        var accounts: List<Account> = emptyList(),

        @Column(length = 50, nullable = true)
        var phoneNumber: String? = ""
) : BaseEntity() {
    fun addAccount(account: Account) {
        account.user = this
        accounts = accounts.plus(account)
    }
}