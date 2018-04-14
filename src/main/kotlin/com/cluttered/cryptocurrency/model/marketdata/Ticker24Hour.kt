package com.cluttered.cryptocurrency.model.marketdata

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class Ticker24Hour(
        val symbol: String,
        val priceChange: BigDecimal,
        val priceChangePercent: BigDecimal,
        val weightedAvgPrice: BigDecimal,
        val prevClosePrice: BigDecimal,
        val lastPrice: BigDecimal,
        @SerializedName("lastQty") val lastQuantity: BigDecimal,
        val bidPrice: BigDecimal,
        val askPrice: BigDecimal,
        val openPrice: BigDecimal,
        val highPrice: BigDecimal,
        val lowPrice: BigDecimal,
        val volume: BigDecimal,
        val quoteVolume: BigDecimal,
        val openTime: Long,
        val closeTime: Long,
        val fristId: Long,
        val lastId: Long,
        val count: Long
)