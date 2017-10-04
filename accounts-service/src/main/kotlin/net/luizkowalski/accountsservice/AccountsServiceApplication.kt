package net.luizkowalski.accountsservice

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
class AccountsServiceApplication

fun main(args: Array<String>) {
    SpringApplication.run(AccountsServiceApplication::class.java, *args)
}
