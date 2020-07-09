package com.skkap.webtr.producer

import com.linecorp.armeria.server.docs.DocService
import com.linecorp.armeria.server.logging.AccessLogWriter
import com.linecorp.armeria.server.logging.LoggingService
import com.linecorp.armeria.spring.ArmeriaServerConfigurator
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class WebTransactionConfiguration {
    @Bean
    fun armeriaServerConfigurator(service: WebTransactionService): ArmeriaServerConfigurator =
        // Customize the server using the given ServerBuilder. For example:
        ArmeriaServerConfigurator {
            // Add DocService that enables you to send Thrift and gRPC requests from web browser.
            it.serviceUnder("/docs", DocService())

            // Log every message which the server receives and responds.
            it.decorator(LoggingService.newDecorator())

            // Write access log after completing a request.
            it.accessLogWriter(AccessLogWriter.combined(), false)

            // Add an Armeria annotated HTTP service.
            it.annotatedService(service)
        }
}
