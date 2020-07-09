package com.skkap.webtr.producer

import com.linecorp.armeria.server.annotation.Get
import com.skkap.webtr.producer.model.WebTransaction
import org.springframework.stereotype.Component

@Component
class WebTransactionService(
    private val webTransactionProducer: WebTransactionProducer
) {
    @Get("/buy")
    fun buy(userId: String): String {
        webTransactionProducer.produce(WebTransaction(userId, "buy"))
        return "OK"
    }
}
