package com.cluttered.cryptocurrency.model.ws

import com.cluttered.cryptocurrency.model.marketdata.Depth
import com.google.gson.annotations.SerializedName

data class DepthUpdateEvent(
        @SerializedName("e") val event: String,
        @SerializedName("E") val time: Long,
        @SerializedName("s") val symbol: String,
        @SerializedName("U") val firstUpdateId: Long,
        @SerializedName("u") val finalUpdateId: Long,
        @SerializedName("b") val bids: List<Depth.Order>,
        @SerializedName("a") val asks: List<Depth.Order>
)