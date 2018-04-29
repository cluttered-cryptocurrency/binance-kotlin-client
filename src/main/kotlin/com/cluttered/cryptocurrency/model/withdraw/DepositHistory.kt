package com.cluttered.cryptocurrency.model.withdraw

import java.math.BigDecimal

data class DepositHistory(
        val depositList: List<Deposit>,
        val success: Boolean
) {
    data class Deposit(
            val asset: String,
            val amount: BigDecimal,
            val insertTime: Long,
            val address: String,
            val txId: String,
            val status: WithdrawStatus)
}