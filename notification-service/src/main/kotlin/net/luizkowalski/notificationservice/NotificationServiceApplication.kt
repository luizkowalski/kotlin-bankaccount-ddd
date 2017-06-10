package net.luizkowalski.notificationservice

import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableRabbit
@EnableDiscoveryClient
@EnableAsync
class NotificationServiceApplication

fun main(args: Array<String>) {
    SpringApplication.run(NotificationServiceApplication::class.java, *args)
}
