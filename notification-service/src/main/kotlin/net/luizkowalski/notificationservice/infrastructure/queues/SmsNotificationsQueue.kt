package net.luizkowalski.notificationservice.infrastructure.queues

import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SmsNotificationsQueue {

    @Bean
    fun notificationQueue(): Queue {
        return Queue("sms-notifications")
    }
}