package net.luizkowalski.accountsservice.presentation.controllers

import net.luizkowalski.accountsservice.application.services.CreateAccountService
import net.luizkowalski.accountsservice.infrastructure.repositories.UsersRepository
import net.luizkowalski.accountsservice.presentation.models.AccountParam
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/accounts")
class AccountsController(val createAccountService: CreateAccountService,
                         val usersRepository: UsersRepository) {

    @PostMapping
    fun createAccount(@RequestBody @Validated accountParam: AccountParam): ResponseEntity<Any> {
        return try {
            var user = usersRepository.findByPassportNumber(accountParam.passportNumber)
            if (user != null)
                return ResponseEntity("User already exists", HttpStatus.BAD_REQUEST)

            user = createAccountService.createUser(accountParam.name,
                    accountParam.email, accountParam.passportNumber, accountParam.accountType)

            ResponseEntity(user, HttpStatus.CREATED)
        } catch (e: Exception) {
            ResponseEntity.ok("Something went wrong: ${e.localizedMessage}")
        }
    }

    @GetMapping("/mine")
    fun myAccount(): ResponseEntity<Any> {
        return ResponseEntity.ok("")
    }
}