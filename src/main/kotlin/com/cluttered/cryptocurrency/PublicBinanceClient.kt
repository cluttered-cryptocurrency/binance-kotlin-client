package com.cluttered.cryptocurrency

import com.cluttered.cryptocurrency.retrofit.RetrofitFactory
import com.cluttered.cryptocurrency.retrofit.create
import com.cluttered.cryptocurrency.services.GeneralRestService
import com.cluttered.cryptocurrency.services.MarketDataRestService

class PublicBinanceClient {

    private val retrofit = RetrofitFactory.create()

    val general by lazy {
        retrofit.create<GeneralRestService>()
    }

    val marketData by lazy {
        retrofit.create<MarketDataRestService>()
    }
}