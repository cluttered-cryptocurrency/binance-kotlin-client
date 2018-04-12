package com.cluttered.cryptocurrency

import com.cluttered.cryptocurrency.retrofit.RetrofitFactory
import com.cluttered.cryptocurrency.retrofit.create
import com.cluttered.cryptocurrency.services.GeneralRestService
import com.cluttered.cryptocurrency.services.MarketDataRestService
import retrofit2.Retrofit

open class PublicBinanceClient(key: String = "", secret: String = "") {

    protected val retrofit: Retrofit = RetrofitFactory.create(key, secret)

    val general by lazy {
        retrofit.create<GeneralRestService>()
    }

    val marketData by lazy {
        retrofit.create<MarketDataRestService>()
    }
}