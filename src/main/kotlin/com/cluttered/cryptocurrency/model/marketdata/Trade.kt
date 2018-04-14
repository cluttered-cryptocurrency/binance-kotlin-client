package com.cluttered.cryptocurrency.model.marketdata

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class Trade(
        val id: Long,
        val price: BigDecimal,
        @SerializedName("qty") val quantity: BigDecimal,
        val time: Long,
        val isBuyerMaker: Boolean,
        val isBestMatch: Boolean
)