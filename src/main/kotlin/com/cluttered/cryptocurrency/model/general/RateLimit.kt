package com.cluttered.cryptocurrency.model.general

import com.cluttered.cryptocurrency.model.RateLimitInterval
import com.cluttered.cryptocurrency.model.RateLimitType

data class RateLimit(
        val rateLimitType: RateLimitType,
        val interval: RateLimitInterval,
        val limit: Int
)