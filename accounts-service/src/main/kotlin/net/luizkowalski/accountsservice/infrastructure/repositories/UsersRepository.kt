package net.luizkowalski.accountsservice.infrastructure.repositories

import net.luizkowalski.accountsservice.domain.user.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UsersRepository : CrudRepository<User, Long> {
    fun findByPassportNumber(passportNumber: String): User?
}