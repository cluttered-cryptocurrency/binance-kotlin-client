package com.cluttered.cryptocurrency.model.account

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class Trade(
        val id: Long,
        val orderId: Long,
        val price: BigDecimal,
        @SerializedName("qty") val quantity: BigDecimal,
        val commission: BigDecimal,
        val commissionAsset: String,
        val time: Long,
        val isBuyer: Boolean,
        val isMaker: Boolean,
        val isBestMatch: Boolean
)