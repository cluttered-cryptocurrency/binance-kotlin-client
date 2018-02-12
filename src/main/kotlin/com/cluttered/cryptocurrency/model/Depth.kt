package com.cluttered.cryptocurrency.model

import java.math.BigDecimal
import java.math.BigInteger

data class Depth constructor(
        val lastUpdateId: BigInteger,
        val bids: MutableList<Order>,
        val asks: MutableList<Order>
) {

    data class Order(
            val price: BigDecimal,
            val quantity: BigDecimal
    )
}