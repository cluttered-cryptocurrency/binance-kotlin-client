package com.cluttered.cryptocurrency.model.marketdata

import java.math.BigDecimal
import java.time.Instant

data class Candlestick(
        val openTime: Instant,
        val open: BigDecimal,
        val high: BigDecimal,
        val low: BigDecimal,
        val close: BigDecimal,
        val volume: BigDecimal,
        val closeTime: Instant,
        val quoteAssetVolume: BigDecimal,
        val numberOfTrades: Long,
        val takerBuyBaseAssetVolume: BigDecimal,
        val takerBuyQuoteAssetVolume: BigDecimal
)