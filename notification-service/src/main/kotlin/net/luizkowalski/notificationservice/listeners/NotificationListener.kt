package net.luizkowalski.notificationservice.listeners

import com.twilio.Twilio
import com.twilio.rest.api.v2010.account.Message
import com.twilio.type.PhoneNumber
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component


@Component
class NotificationListener {

    private val logger = LoggerFactory.getLogger(NotificationListener::class.java)

    @RabbitListener(queues = arrayOf("sms-notifications"))
    @Async
    fun sendNotification(payload: Map<String, String>) {
        println(payload)
        logger.info("Notifying client")
        Message.creator(PhoneNumber(payload.get("phoneNumber")),
                PhoneNumber("+13476575331"), payload.get("text")).create()
    }

    init {
        Twilio.init("AC0f1f85064ffdd5ec021ab28fd10c339c", "de559821646717b714edcf83587283b8")
    }
}