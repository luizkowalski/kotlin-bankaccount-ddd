package net.luizkowalski.accountsservice

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
@EnableScheduling
class AccountsServiceApplication

fun main(args: Array<String>) {
    SpringApplication.run(AccountsServiceApplication::class.java, *args)
}
