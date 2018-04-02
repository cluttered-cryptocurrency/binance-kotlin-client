package com.cluttered.cryptocurrency.model

import com.cluttered.cryptocurrency.model.enum.RateLimitInterval
import com.cluttered.cryptocurrency.model.enum.RateLimitType

data class RateLimit(
        val rateLimitType: RateLimitType,
        val interval: RateLimitInterval,
        val limit: Int
)