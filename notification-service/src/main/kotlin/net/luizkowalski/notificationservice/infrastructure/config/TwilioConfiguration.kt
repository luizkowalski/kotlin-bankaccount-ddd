package net.luizkowalski.notificationservice.infrastructure.config

import com.twilio.Twilio
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration


@Configuration
class TwilioConfiguration(
        @Value("\${twilio.username}") val user: String,
        @Value("\${twilio.password}") val password: String) {

    init {
        Twilio.init(user, password)
    }
}