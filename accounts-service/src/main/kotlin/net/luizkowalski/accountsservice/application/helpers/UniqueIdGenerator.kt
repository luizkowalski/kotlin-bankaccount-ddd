package net.luizkowalski.accountsservice.application.helpers

import java.security.SecureRandom

object UniqueIdGenerator {
    var ALLOWED = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvzyzw";
    var random = SecureRandom()

    fun nextNumber(length: Int = 20): String {
        val sb = StringBuilder(length)
        for (i in 0 until length) {
            sb.append(ALLOWED.toCharArray()[random.nextInt(ALLOWED.length)])
        }
        return sb.toString()
    }
}