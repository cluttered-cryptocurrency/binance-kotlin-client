package com.cluttered.cryptocurrency.model.marketdata

import java.math.BigDecimal

data class TickerPrice(
        val symbol: String,
        val price: BigDecimal
)