package com.cluttered.cryptocurrency.model.websocket

import com.cluttered.cryptocurrency.model.marketdata.CandlestickInterval
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class CandlestickEvent(
        @SerializedName("e") val event: String,
        @SerializedName("E") val time: Long,
        @SerializedName("s") val symbol: String,
        @SerializedName("k") val candlestick: Candlestick
) {
    data class Candlestick(
            @SerializedName("t") val openTime: Long,
            @SerializedName("T") val closeTime: Long,
            @SerializedName("s") val symbol: String,
            @SerializedName("i") val interval: CandlestickInterval,
            @SerializedName("f") val firstTradeId: Long,
            @SerializedName("L") val lastTradeId: Long,
            @SerializedName("o") val openPrice: BigDecimal,
            @SerializedName("c") val closePrice: BigDecimal,
            @SerializedName("h") val highPrice: BigDecimal,
            @SerializedName("l") val lowPrice: BigDecimal,
            @SerializedName("v") val volume: BigDecimal,
            @SerializedName("n") val numberOfTrades: Long,
            @SerializedName("x") val isClosed: Boolean,
            @SerializedName("q") val quoteAssetVolume: BigDecimal,
            @SerializedName("V") val takerBuyBaseAssetVolume: BigDecimal,
            @SerializedName("Q") val takerBuyQuoteAssetVolume: BigDecimal
    )
}