package net.luizkowalski.notificationservice

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Sink
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync
@EnableBinding(Sink::class)
class NotificationServiceApplication

fun main(args: Array<String>) {
    SpringApplication.run(NotificationServiceApplication::class.java, *args)
}
