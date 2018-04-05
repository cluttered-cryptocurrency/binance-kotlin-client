package com.cluttered.cryptocurrency.model

import com.cluttered.cryptocurrency.model.enums.RateLimitInterval
import com.cluttered.cryptocurrency.model.enums.RateLimitType

data class RateLimit(
        val rateLimitType: RateLimitType,
        val interval: RateLimitInterval,
        val limit: Int
)