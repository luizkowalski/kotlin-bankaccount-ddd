package net.luizkowalski.accountsservice.infrastructure.config

import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Source

@EnableBinding(Source::class)
class StreamConfiguration {}