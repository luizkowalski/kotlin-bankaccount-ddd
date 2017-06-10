package net.luizkowalski.accountsservice.presentation.controllers

import net.luizkowalski.accountsservice.application.exceptions.AccountNotFoundException
import net.luizkowalski.accountsservice.application.exceptions.AmountNotPossibleException
import net.luizkowalski.accountsservice.application.services.CreateTransactionService
import net.luizkowalski.accountsservice.presentation.models.TransactionsParam
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transactions")
class TransactionsController(val transactionService: CreateTransactionService) {

    @PostMapping
    fun createTransaction(@RequestBody @Validated params: TransactionsParam): ResponseEntity<Any> {
        try {
            var transaction = transactionService.createTransaction(params.iban, params.recipient, params.amount)
            return ResponseEntity(transaction, HttpStatus.CREATED)
        } catch (e: AmountNotPossibleException) {
            return ResponseEntity(e.localizedMessage, HttpStatus.CREATED)
        } catch (e: AccountNotFoundException) {
            return ResponseEntity(e.localizedMessage, HttpStatus.BAD_REQUEST)
        } catch(e: Exception) {
            return ResponseEntity("An error occurred while finalising your transactions. " +
                    "The money did not leave your account: ${e.localizedMessage}", HttpStatus.BAD_REQUEST)
        }
    }
}