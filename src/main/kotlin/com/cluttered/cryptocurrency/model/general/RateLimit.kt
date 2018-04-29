package com.cluttered.cryptocurrency.model.general

data class RateLimit(
        val rateLimitType: RateLimitType,
        val interval: RateLimitInterval,
        val limit: Int
)