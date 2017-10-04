package net.luizkowalski.accountsservice.domain.account.saga

import net.luizkowalski.accountsservice.application.services.NotificationService
import net.luizkowalski.accountsservice.domain.account.Account
import net.luizkowalski.accountsservice.domain.account.Flow
import net.luizkowalski.accountsservice.domain.account.Transaction
import net.luizkowalski.accountsservice.infrastructure.exceptions.AccountNotFoundException
import net.luizkowalski.accountsservice.infrastructure.exceptions.AmountNotPossibleException
import net.luizkowalski.accountsservice.infrastructure.exceptions.TransactionNotPossibleException
import net.luizkowalski.accountsservice.infrastructure.repositories.AccountsRepository
import net.luizkowalski.accountsservice.infrastructure.repositories.FlowsRepository
import net.luizkowalski.accountsservice.infrastructure.repositories.TransactionsRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class CreateTransactionSaga(
        val accountsRepository: AccountsRepository,
        val transactionsRepository: TransactionsRepository,
        val flowsRepository: FlowsRepository,
        val notificationService: NotificationService) {

    @Transactional
    fun createTransaction(from: String, to: String, amount: Long): Transaction {
        if (from == to)
            throw TransactionNotPossibleException("You can't send money to yourself")

        val sender = accountsRepository.findByIban(from) ?: throw AccountNotFoundException("Account not found")
        val destination = accountsRepository.findByIban(to) ?: throw AccountNotFoundException("Account not found")

        if (sender.balance < amount)
            throw AmountNotPossibleException("The account $from do not have enough money")

        val f = Flow()
        f.accountFrom = from
        f.accountTo = to
        f.amount = amount
        f.flowId = UUID.randomUUID().toString()

        sender.balance -= amount
        destination.balance += amount

        val senderTransaction = create(amount * -1, sender, f.flowId)
        val destinationTransaction = create(amount, destination, f.flowId)

        flowsRepository.save(f) // Remove it
        accountsRepository.save(arrayListOf(sender, destination))
        transactionsRepository.save(arrayListOf(senderTransaction, destinationTransaction)) // Remove it
        notificationService.notifyTransaction(destinationTransaction) // Remove it
        return senderTransaction
    }

    private fun create(amount: Long, account: Account, flowId: String): Transaction {
        return Transaction(
                amount = amount,
                account = account,
                currency = "EUR",
                flowId = flowId,
                transactionId = UUID.randomUUID().toString())
    }
}