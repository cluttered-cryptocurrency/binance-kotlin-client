package com.cluttered.cryptocurrency.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal
import java.time.Instant

data class Trade(
        val id: Long,
        val price: BigDecimal,
        @SerializedName("qty") val quantity: BigDecimal,
        val time: Instant,
        val isBuyerMaker: Boolean,
        val isBestMatch: Boolean
)