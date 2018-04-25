package com.cluttered.cryptocurrency.model.marketdata

import java.math.BigDecimal
import java.math.BigInteger

data class Depth(
        val lastUpdateId: BigInteger,
        val bids: List<Order>,
        val asks: List<Order>
) {

    data class Order(
            val price: BigDecimal,
            val quantity: BigDecimal
    )
}