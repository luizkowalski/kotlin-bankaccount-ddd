package net.luizkowalski.accountsservice.application.services

import net.luizkowalski.accountsservice.domain.Transaction
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class NotificationService(val rabbitTemplate: RabbitTemplate) {

    @Async
    fun notifyReceived(t: Transaction) {
        var number = t.account?.user?.phoneNumber ?: return;
        var text = "You received ${t.amount} ${t.currency} on ${t.createdAt}"
        var message = mapOf("text" to text, "phoneNumber" to number)
        rabbitTemplate.convertSendAndReceive("sms-notifications", message)
    }
}