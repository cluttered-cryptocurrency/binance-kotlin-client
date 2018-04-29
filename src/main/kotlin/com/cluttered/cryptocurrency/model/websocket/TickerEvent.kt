package com.cluttered.cryptocurrency.model.websocket

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class TickerEvent(
        @SerializedName("e") val event: String,
        @SerializedName("E") val time: Long,
        @SerializedName("s") val symbol: String,
        @SerializedName("p") val priceChange: BigDecimal,
        @SerializedName("P") val priceChangePercent: BigDecimal,
        @SerializedName("v") val weightedAvgPrice: BigDecimal,
        @SerializedName("x") val previousClosePrice: BigDecimal,
        @SerializedName("c") val currentClosePrice: BigDecimal,
        @SerializedName("Q") val closeTradeQuantity: BigDecimal,
        @SerializedName("b") val bestBidPrice: BigDecimal,
        @SerializedName("B") val bestBidQuantity: BigDecimal,
        @SerializedName("a") val bestAskPrice: BigDecimal,
        @SerializedName("A") val bestAskQuantity: BigDecimal,
        @SerializedName("o") val openPrice: BigDecimal,
        @SerializedName("h") val highPrice: BigDecimal,
        @SerializedName("l") val lowPrice: BigDecimal,
        @SerializedName("v") val volume: BigDecimal,
        @SerializedName("q") val quoteAssetVolume: BigDecimal,
        @SerializedName("O") val statisticsOpenTime: Long,
        @SerializedName("C") val statisticsCloseTime: Long,
        @SerializedName("F") val firstTradeId: Long,
        @SerializedName("L") val lastTradeId: Long,
        @SerializedName("n") val numberOfTrade: Long
)