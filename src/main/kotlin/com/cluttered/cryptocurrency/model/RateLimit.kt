package com.cluttered.cryptocurrency.model

import com.cluttered.cryptocurrency.model.enum.RateLimitInterval
import com.cluttered.cryptocurrency.model.enum.RateLimitType
import com.google.gson.annotations.SerializedName

data class RateLimit(
        @SerializedName("rateLimitType") val type: RateLimitType,
        val interval: RateLimitInterval,
        val limit: Int
)