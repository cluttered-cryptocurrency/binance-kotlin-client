package com.cluttered.cryptocurrency.model.withdraw

import java.math.BigDecimal

data class WithdrawHistory(
        val withdrawList: List<Withdraw>,
        val success: Boolean
) {
    data class Withdraw(
            val id: String,
            val amount: BigDecimal,
            val address: String,
            val asset: String,
            val txId: String,
            val applyTime: Long,
            val status: WithdrawStatus
    )
}