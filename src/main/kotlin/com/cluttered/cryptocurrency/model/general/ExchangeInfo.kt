package com.cluttered.cryptocurrency.model.general

data class ExchangeInfo(
        val timezone: String,
        val serverTime: Long,
        val rateLimits: List<RateLimit>,
        val exchangeFilters: List<Any>,
        val symbols: List<Symbol>
)