package com.cluttered.cryptocurrency.model.general

import com.cluttered.cryptocurrency.model.OrderType
import com.cluttered.cryptocurrency.model.SymbolStatus

data class Symbol(
        val symbol: String,
        val status: SymbolStatus,
        val baseAsset: String,
        val baseAssetPrecision: Int,
        val quoteAsset: String,
        val quotePrecision: Int,
        val orderTypes: MutableList<OrderType>,
        val icebergAllowed: Boolean,
        val filters: MutableList<Any>
)