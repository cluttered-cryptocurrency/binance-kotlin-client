package com.cluttered.cryptocurrency.model.account

import com.google.gson.annotations.SerializedName

data class CancelledOrder(
        val symbol: String,
        @SerializedName("origClientOrderId") val originalClientOrderId: String,
        val orderId: Long,
        val clientOrderId: String
)