package com.skkap.webtr.producer

import com.linecorp.decaton.client.DecatonClient
import com.linecorp.decaton.common.Serializer
import com.skkap.webtr.producer.model.WebTransaction
import kotlinx.serialization.protobuf.ProtoBuf
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
class DecatonConfiguration {
    @Bean
    fun decatonClient(): DecatonClient<WebTransaction> {
        val producerConfig = Properties()
        producerConfig.setProperty(ProducerConfig.CLIENT_ID_CONFIG, "webtr-producer-client-id")
        producerConfig.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")

        return DecatonClient
            .producing(
                WEB_TRANSACTION_TOPIC,
                Serializer<WebTransaction> { data -> ProtoBuf.dump(WebTransaction.serializer(), data) }
            )
            .applicationId("webtr-producer-app")
            .producerConfig(producerConfig)
            .build()
    }

    companion object {
        private const val WEB_TRANSACTION_TOPIC = "web-transaction-v1"
    }
}
