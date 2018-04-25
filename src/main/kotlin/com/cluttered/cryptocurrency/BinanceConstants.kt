package com.cluttered.cryptocurrency

object BinanceConstants {

    var BASE_REST_URL: String = "https://api.binance.com"

    const val BASE_WEB_SOCKET_URL = "wss://stream.binance.com:9443"

    /**
     * HTTP Header to be used for API-KEY authentication.
     */
    const val API_KEY_HEADER = "X-MBX-APIKEY"

    /**
     * Decorator to indicate that an endpoint requires an API key.
     */
    const val ENDPOINT_SECURITY_TYPE_APIKEY = "APIKEY"
    const val ENDPOINT_SECURITY_TYPE_APIKEY_HEADER = "$ENDPOINT_SECURITY_TYPE_APIKEY: #"

    /**
     * Decorator to indicate that an endpoint requires a signature.
     */
    const val ENDPOINT_SECURITY_TYPE_SIGNED = "SIGNED"
    const val ENDPOINT_SECURITY_TYPE_SIGNED_HEADER = "$ENDPOINT_SECURITY_TYPE_SIGNED: #"
}