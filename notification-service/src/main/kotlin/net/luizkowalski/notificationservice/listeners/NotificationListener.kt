package net.luizkowalski.notificationservice.listeners

import com.twilio.rest.api.v2010.account.Message
import com.twilio.type.PhoneNumber
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Sink
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component


@Component
@Async
class NotificationListener(@Value("\${twilio.phoneNumber}") val phoneNumber: String) {
    private val logger = LoggerFactory.getLogger(NotificationListener::class.java)

    @StreamListener(target = Sink.INPUT)
    fun sendNotification(payload: Map<String, String>) {
        logger.info("Notifying client")
        Message.creator(
                PhoneNumber(payload.get("phoneNumber")),
                PhoneNumber(phoneNumber),
                payload.get("text")).create()
    }
}