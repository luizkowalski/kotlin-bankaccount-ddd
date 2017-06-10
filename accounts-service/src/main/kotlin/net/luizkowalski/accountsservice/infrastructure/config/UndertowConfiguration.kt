package net.luizkowalski.accountsservice.infrastructure.config

import org.slf4j.LoggerFactory
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory
import org.springframework.context.annotation.Configuration

@Configuration
class UndertowConfiguration : EmbeddedServletContainerCustomizer {
    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun customize(container: ConfigurableEmbeddedServletContainer) {
        if (UndertowEmbeddedServletContainerFactory::class.java.isAssignableFrom(container::class.java)) {
            log.info("Undertow detected")
            val cores = Runtime.getRuntime().availableProcessors()
            val undertowContainer = UndertowEmbeddedServletContainerFactory::class.java.cast(container)
            undertowContainer.setIoThreads(cores);
            undertowContainer.setWorkerThreads(cores * 10);
            undertowContainer.setBufferSize(16384)
            log.info("Undertow configured with $cores cores")
        } else {
            log.info("Not using Undertow")
        }
    }
}