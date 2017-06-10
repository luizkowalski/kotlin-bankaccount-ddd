package net.luizkowalski.accountsservice.application.helpers

import java.security.SecureRandom

object AccountNumberGenerator {
    var ALLOWED = "0123456789";
    var random = SecureRandom()

    fun nextNumber(): String {
        val sb = StringBuilder(14)
        for (i in 0..14 - 1) {
            sb.append(ALLOWED.toCharArray()[random.nextInt(ALLOWED.length)])
        }
        return sb.toString()
    }
}