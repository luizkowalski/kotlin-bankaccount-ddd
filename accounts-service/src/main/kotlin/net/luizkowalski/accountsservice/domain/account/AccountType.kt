package net.luizkowalski.accountsservice.domain.account

enum class AccountType(val type: Int) {
    CHECKING(0),
    SAVINGS(1);

    companion object {
        fun fromCode(code: Int): AccountType {
            return AccountType.values().filter { it.type == code }[0]
        }
    }
}