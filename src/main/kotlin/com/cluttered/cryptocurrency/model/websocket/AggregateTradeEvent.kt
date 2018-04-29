package com.cluttered.cryptocurrency.model.websocket

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class AggregateTradeEvent(
        @SerializedName("e") val event: String,
        @SerializedName("E") val time: Long,
        @SerializedName("s") val symbol: String,
        @SerializedName("a") val id: Long,
        @SerializedName("p") val price: BigDecimal,
        @SerializedName("q") val quantity: BigDecimal,
        @SerializedName("f") val firstTradeId: Long,
        @SerializedName("l") val lastTradeId: Long,
        @SerializedName("T") val tradeTime: Long,
        @SerializedName("m") val wasMaker: Boolean
)