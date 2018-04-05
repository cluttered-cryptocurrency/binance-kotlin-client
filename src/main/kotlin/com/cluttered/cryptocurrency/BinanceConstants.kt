package com.cluttered.cryptocurrency

import java.util.concurrent.TimeUnit

object BinanceConstants {

    var BASE_REST_URL: String = "https://rest.binance.com"

    val RECEIVING_WINDOW_1_MINUTE = TimeUnit.MINUTES.toMillis(1)
}