package com.cluttered.cryptocurrency.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class TickerBook(
        val symbol: String,
        val bidPrice: BigDecimal,
        @SerializedName("bidQty") val bidQuantity: BigDecimal,
        val askPrice: BigDecimal,
        @SerializedName("askQty") val askQuantity: BigDecimal
)