package com.cluttered.cryptocurrency

import java.util.concurrent.TimeUnit

object BinanceConstants {

    var BASE_REST_URL: String = "https://rest.binance.com"

    val MINUTE_IN_MILLIS = TimeUnit.MINUTES.toMillis(1)
}