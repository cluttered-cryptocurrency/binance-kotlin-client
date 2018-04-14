package com.cluttered.cryptocurrency.model.account

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class AccountSnapshot(
        val makerCommission: Long,
        val takerCommission: Long,
        val buyerCommission: Long,
        val sellerCommission: Long,
        val canTrade: Boolean,
        val canWithdraw: Boolean,
        val canDeposit: Boolean,
        val updateTime: Long,
        val balances: MutableList<Asset>
) {
    data class Asset(
            @SerializedName("asset") val symbol: String,
            val free: BigDecimal,
            val locked: BigDecimal
    )
}