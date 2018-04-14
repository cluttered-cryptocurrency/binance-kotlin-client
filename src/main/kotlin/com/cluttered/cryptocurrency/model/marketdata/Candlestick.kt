package com.cluttered.cryptocurrency.model.marketdata

import java.math.BigDecimal

data class Candlestick(
        val openTime: Long,
        val open: BigDecimal,
        val high: BigDecimal,
        val low: BigDecimal,
        val close: BigDecimal,
        val volume: BigDecimal,
        val closeTime: Long,
        val quoteAssetVolume: BigDecimal,
        val numberOfTrades: Long,
        val takerBuyBaseAssetVolume: BigDecimal,
        val takerBuyQuoteAssetVolume: BigDecimal
)