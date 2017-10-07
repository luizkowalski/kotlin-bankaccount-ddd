package net.luizkowalski.accountsservice.presentation.controllers

import net.luizkowalski.accountsservice.domain.account.services.FundTransferService
import net.luizkowalski.accountsservice.infrastructure.exceptions.AccountNotFoundException
import net.luizkowalski.accountsservice.infrastructure.exceptions.NotEnoughFunds
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
class TransactionsController(val transferService: FundTransferService) {

    @PostMapping
    fun createTransaction(@RequestBody @Validated params: TransactionsParam): ResponseEntity<Any> {
        return try {
            transferService.transfer(params.origin, params.destination, params.amount, params.reason)
            ResponseEntity.ok().build()
        } catch (e: NotEnoughFunds) {
            ResponseEntity(e.localizedMessage, HttpStatus.PRECONDITION_FAILED)
        } catch (e: AccountNotFoundException) {
            ResponseEntity(e.localizedMessage, HttpStatus.PRECONDITION_FAILED)
        } catch (e: Exception) {
            ResponseEntity("An error occurred while finalising your transactions. " +
                    "The money did not leave your account: ${e.localizedMessage}", HttpStatus.BAD_REQUEST)
        }
    }
}