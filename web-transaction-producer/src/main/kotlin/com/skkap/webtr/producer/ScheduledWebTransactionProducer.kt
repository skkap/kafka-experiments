package com.skkap.webtr.producer

import com.skkap.webtr.producer.model.WebTransaction
import org.springframework.scheduling.annotation.Scheduled
import javax.inject.Named
import kotlin.random.Random


@Named
class ScheduledWebTransactionProducer(
    private val webTransactionProducer: WebTransactionProducer
) {
    @Scheduled(fixedRate = 1000)
    fun reportCurrentTime() {
        val userId = "user" + Random.nextInt(0, 5)
        webTransactionProducer.produce(WebTransaction("buy", userId))
    }
}
