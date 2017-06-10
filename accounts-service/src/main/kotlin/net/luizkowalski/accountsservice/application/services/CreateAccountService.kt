package net.luizkowalski.accountsservice.application.services

import net.luizkowalski.accountsservice.application.helpers.AccountNumberGenerator
import net.luizkowalski.accountsservice.domain.Account
import net.luizkowalski.accountsservice.domain.AccountType
import net.luizkowalski.accountsservice.domain.User
import net.luizkowalski.accountsservice.infrastructure.repositories.UsersRepository
import org.iban4j.CountryCode
import org.iban4j.Iban
import org.springframework.stereotype.Service

@Service
class CreateAccountService(val usersRepository: UsersRepository) {
    fun createUser(name: String, email: String, passportNumber: String, accountType: Int): User {
        var user = User()
        user.email = email
        user.name = name
        user.passportNumber = passportNumber

        user = usersRepository.save(user)
        user.addAccount(createAccount(user, accountType))
        return usersRepository.save(user)
    }

    private fun createAccount(user: User, accountType: Int): Account {
        var account = Account()
        var number: String = AccountNumberGenerator.nextNumber()
        account.accountType = AccountType.fromCode(accountType)
        account.number = number
        account.iban = newIBAN(number)

        return account
    }

    private fun newIBAN(number: String): String {
        return Iban.Builder()
                .accountNumber(number)
                .bankCode("1234")
                .countryCode(CountryCode.DE)
                .build()
                .toString()
    }
}