package net.luizkowalski.accountsservice.infrastructure.exceptions

class AccountNotFoundException(account: String) : RuntimeException("Account $account was not found")