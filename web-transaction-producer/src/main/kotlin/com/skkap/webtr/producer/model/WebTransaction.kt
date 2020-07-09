package com.skkap.webtr.producer.model

import kotlinx.serialization.Serializable

@Serializable
data class WebTransaction(
    val type: String,

    val userId: String
)
