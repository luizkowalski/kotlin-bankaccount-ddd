package net.luizkowalski.accountsservice.infrastructure.repositories

import net.luizkowalski.accountsservice.domain.account.Transaction
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionsRepository : CrudRepository<Transaction, Long> {
}