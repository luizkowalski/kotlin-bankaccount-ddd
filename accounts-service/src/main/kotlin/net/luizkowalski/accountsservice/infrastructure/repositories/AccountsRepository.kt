package net.luizkowalski.accountsservice.infrastructure.repositories

import net.luizkowalski.accountsservice.domain.Account
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountsRepository : CrudRepository<Account, Long> {
    fun findByIban(iban: String): Account?
}