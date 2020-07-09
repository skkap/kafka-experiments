package com.skkap.webtr.consumer

import com.linecorp.decaton.common.Deserializer
import com.linecorp.decaton.processor.ProcessorProperties.CONFIG_MAX_PENDING_RECORDS
import com.linecorp.decaton.processor.ProcessorProperties.CONFIG_PARTITION_CONCURRENCY
import com.linecorp.decaton.processor.ProcessorsBuilder
import com.linecorp.decaton.processor.Property
import com.linecorp.decaton.processor.StaticPropertySupplier
import com.linecorp.decaton.processor.metrics.Metrics
import com.linecorp.decaton.processor.runtime.ProcessorSubscription
import com.linecorp.decaton.processor.runtime.SubscriptionBuilder
import com.skkap.webtr.consumer.model.WebTransaction
import kotlinx.serialization.protobuf.ProtoBuf
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
class DecatonConfiguration {
    @Bean
    fun processorSubscription(): ProcessorSubscription {
        val consumerConfig = Properties()
        consumerConfig.setProperty(ConsumerConfig.CLIENT_ID_CONFIG, "webtr-consumer-client-id")
        consumerConfig.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "webtr-consumer-group-id")
        consumerConfig.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")

        val processorsBuilder = ProcessorsBuilder.consuming(
            WEB_TRANSACTION_TOPIC,
            Deserializer { bytes -> ProtoBuf.load(WebTransaction.serializer(), bytes) }
        )
            .thenProcess(WebTransactionProcessor())

        val builder = SubscriptionBuilder
            .newBuilder("webtr-consumer-subscription-id")
            .consumerConfig(consumerConfig)
            .properties(
                StaticPropertySupplier.of(Property.ofStatic(CONFIG_PARTITION_CONCURRENCY, 5)),
                StaticPropertySupplier.of(Property.ofStatic(CONFIG_MAX_PENDING_RECORDS, 100))
            )
            .processorsBuilder(processorsBuilder)
        return builder.buildAndStart()
    }

    companion object {
        private const val WEB_TRANSACTION_TOPIC = "web-transaction-v1"
    }
}
