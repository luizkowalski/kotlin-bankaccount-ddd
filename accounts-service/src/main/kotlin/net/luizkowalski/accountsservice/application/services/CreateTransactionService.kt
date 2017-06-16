package net.luizkowalski.accountsservice.application.services

import net.luizkowalski.accountsservice.application.exceptions.AccountNotFoundException
import net.luizkowalski.accountsservice.application.exceptions.AmountNotPossibleException
import net.luizkowalski.accountsservice.application.exceptions.TransactionNotPossibleException
import net.luizkowalski.accountsservice.application.helpers.UniqueIdGenerator
import net.luizkowalski.accountsservice.domain.Account
import net.luizkowalski.accountsservice.domain.Flow
import net.luizkowalski.accountsservice.domain.Transaction
import net.luizkowalski.accountsservice.infrastructure.repositories.AccountsRepository
import net.luizkowalski.accountsservice.infrastructure.repositories.FlowsRepository
import net.luizkowalski.accountsservice.infrastructure.repositories.TransactionsRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CreateTransactionService(
        val accountsRepository: AccountsRepository,
        val transactionsRepository: TransactionsRepository,
        val flowsRepository: FlowsRepository,
        val notificationService: NotificationService) {

    @Transactional
    fun createTransaction(from: String, to: String, amount: Long): Transaction {
        val accountFrom = accountsRepository.findByIban(from) ?: throw AccountNotFoundException("Account not found")
        val accountTo = accountsRepository.findByIban(to) ?: throw AccountNotFoundException("Account not found")
        if (accountTo.equals(accountFrom))
            throw TransactionNotPossibleException("You can't send money to yourself")

        if (accountFrom.balance < amount)
            throw AmountNotPossibleException("The account $from do not have enough money")

        val f = Flow()
        f.accountFrom = from
        f.accountTo = to
        f.amount = amount
        f.flowId = UniqueIdGenerator.nextNumber(25)

        accountFrom.balance -= amount
        accountTo.balance += amount

        val fromTransaction = create(amount * -1, accountFrom, f.flowId)
        val toTransaction = create(amount, accountTo, f.flowId)

        flowsRepository.save(f)
        accountsRepository.save(arrayListOf(accountFrom, accountTo))
        transactionsRepository.save(arrayListOf(fromTransaction, toTransaction))
        notificationService.notifyTransaction(toTransaction)
        return fromTransaction
    }

    private fun create(amount: Long, account: Account, flowId: String): Transaction {
        return Transaction(amount = amount, account = account, currency = "EUR", flowId = flowId, transactionId = UniqueIdGenerator.nextNumber(25))
    }
}