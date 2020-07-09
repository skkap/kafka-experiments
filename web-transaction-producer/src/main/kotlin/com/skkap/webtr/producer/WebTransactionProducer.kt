package com.skkap.webtr.producer

import com.linecorp.decaton.client.DecatonClient
import com.skkap.webtr.producer.model.WebTransaction
import javax.inject.Named

@Named
class WebTransactionProducer(
    private val producer: DecatonClient<WebTransaction>
) {
    fun produce(webTransaction: WebTransaction) {
        // If we need non-blocking producer we better return CompletableFuture.
        // We use userId is a partitioning key as order of events for a user might matter.
        producer.put(webTransaction.userId, webTransaction).get()
    }
}
