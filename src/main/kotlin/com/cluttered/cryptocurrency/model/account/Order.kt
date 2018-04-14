package com.cluttered.cryptocurrency.model.account

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class Order(
        val symbol: String,
        val orderId: Long,
        val clientOrderId: String,
        val price: BigDecimal,
        @SerializedName("origQty") val originalQuantity: BigDecimal,
        @SerializedName("executedQty") val executedQuantity: BigDecimal,
        val status: OrderStatus,
        val timeInForce: TimeInForce,
        val type: OrderType,
        val side: OrderSide,
        val stopPrice: BigDecimal,
        @SerializedName("icebergQty") val icebergQuantity: BigDecimal,
        val time: Long,
        val isWorking: Boolean
)