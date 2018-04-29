package com.cluttered.cryptocurrency.model.withdraw

import java.math.BigDecimal

data class WithdrawFee(
        val withdrawFee: BigDecimal,
        val success: Boolean
)