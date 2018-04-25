package com.cluttered.cryptocurrency.model.ws

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class TradeEvent(
        @SerializedName("e") val event: String,
        @SerializedName("E") val time: Long,
        @SerializedName("s") val symbol: String,
        @SerializedName("t") val id: Long,
        @SerializedName("p") val price: BigDecimal,
        @SerializedName("q") val quantity: BigDecimal,
        @SerializedName("b") val buyOrderId: Long,
        @SerializedName("a") val sellOrderId: Long,
        @SerializedName("T") val tradeTime: Long,
        @SerializedName("m") val wasMaker: Boolean
)