package com.cluttered.cryptocurrency.model

import java.math.BigDecimal

data class TickerPrice(
        val symbol: String,
        val price: BigDecimal
)