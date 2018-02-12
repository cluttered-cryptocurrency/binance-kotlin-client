package com.cluttered.cryptocurrency.model

import java.time.Instant

data class ExchangeInfo(
        val timezone: String,
        val serverTime: Instant,
        val rateLimits: MutableList<RateLimit>,
        val exchangeFilters: MutableList<Any>,
        val symbols: MutableList<Symbol>
)