package com.cluttered.cryptocurrency.model.account

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
        val balances: List<Asset>
) {
    data class Asset(
            val asset: String,
            val free: BigDecimal,
            val locked: BigDecimal
    )
}