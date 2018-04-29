package com.cluttered.cryptocurrency.model.marketdata

enum class DepthLevel(private val display: String) {
    FIVE("5"),
    TEN("10"),
    TWENTY("20");

    override fun toString(): String {
        return display
    }
}