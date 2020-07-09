package com.skkap.webtr.consumer.model

import kotlinx.serialization.Serializable

@Serializable
data class WebTransaction(
    val type: String,
    val userId: String
)
