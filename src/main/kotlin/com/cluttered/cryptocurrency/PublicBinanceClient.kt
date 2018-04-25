package com.cluttered.cryptocurrency

import com.cluttered.cryptocurrency.retrofit.RetrofitFactory
import com.cluttered.cryptocurrency.retrofit.create
import com.cluttered.cryptocurrency.services.GeneralService
import com.cluttered.cryptocurrency.services.MarketDataService
import com.cluttered.cryptocurrency.ws.RxWebSocket
import retrofit2.Retrofit

open class PublicBinanceClient protected constructor(key: String = "", secret: String = "") {

    companion object {
        @JvmStatic
        fun create(): PublicBinanceClient = PublicBinanceClient()
    }

    protected val retrofit: Retrofit = RetrofitFactory.create(key, secret)

    val general by lazy {
        retrofit.create<GeneralService>()
    }

    val marketData by lazy {
        retrofit.create<MarketDataService>()
    }

    val websocket = RxWebSocket
}