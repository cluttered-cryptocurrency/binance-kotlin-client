package com.cluttered.cryptocurrency.model.general

data class ExchangeInfo(
        val timezone: String,
        val serverTime: Long,
        val rateLimits: MutableList<RateLimit>,
        val exchangeFilters: MutableList<Any>,
        val symbols: MutableList<Symbol>
)