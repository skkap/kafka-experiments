package com.skkap.webtr.consumer

import com.linecorp.decaton.processor.DecatonProcessor
import com.linecorp.decaton.processor.ProcessingContext
import com.skkap.webtr.consumer.model.WebTransaction
import javax.inject.Named

@Named
class WebTransactionProcessor : DecatonProcessor<WebTransaction> {
    @Throws(InterruptedException::class)
    override fun process(context: ProcessingContext<WebTransaction>, task: WebTransaction) {
        println("$task`.")
    }
}
