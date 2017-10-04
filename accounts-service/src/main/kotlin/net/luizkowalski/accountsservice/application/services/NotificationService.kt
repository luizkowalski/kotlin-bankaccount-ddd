package net.luizkowalski.accountsservice.application.services

import net.luizkowalski.accountsservice.domain.account.Transaction
import org.springframework.cloud.stream.messaging.Source
import org.springframework.messaging.support.MessageBuilder
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.text.DateFormat
import java.text.NumberFormat
import java.util.*

@Service
class NotificationService(val source: Source) {

    @Async
    fun notifyTransaction(t: Transaction) {
        var number = t.account?.user?.phoneNumber ?: return;
        var text = "You received ${calculateAmountFromCents(t.amount)} on ${formatDate(t.createdAt!!)}"
        var message = mapOf("text" to text, "phoneNumber" to number)
        source.output().send(MessageBuilder.withPayload(message).build())
    }

    fun calculateAmountFromCents(cents: Long): String {
        return NumberFormat
                .getCurrencyInstance(Locale.GERMANY)
                .format(cents / 100.0)
                .toString()
    }

    fun formatDate(date: Date): String = DateFormat.getDateTimeInstance().format(date)
}