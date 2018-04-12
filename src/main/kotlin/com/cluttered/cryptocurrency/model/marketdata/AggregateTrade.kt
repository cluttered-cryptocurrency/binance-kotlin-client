package com.cluttered.cryptocurrency.model.marketdata

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal
import java.time.Instant

data class AggregateTrade(
        @SerializedName("a") val id: Long,
        @SerializedName("p") val price: BigDecimal,
        @SerializedName("q") val quantity: BigDecimal,
        @SerializedName("f") val firstTradeId: Long,
        @SerializedName("l") val lastTradeId: Long,
        @SerializedName("T") val timestamp: Instant,
        @SerializedName("m") val wasMaker: Boolean,
        @SerializedName("M") val wasBestPrice: Boolean
)