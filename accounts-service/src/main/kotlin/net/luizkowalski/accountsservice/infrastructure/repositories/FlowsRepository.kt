package net.luizkowalski.accountsservice.infrastructure.repositories

import net.luizkowalski.accountsservice.domain.account.Flow
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface FlowsRepository : CrudRepository<Flow, Long> {

}