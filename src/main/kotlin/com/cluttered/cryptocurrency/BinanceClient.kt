package com.cluttered.cryptocurrency

import com.cluttered.cryptocurrency.retrofit.RetrofitFactory
import com.cluttered.cryptocurrency.retrofit.create
import com.cluttered.cryptocurrency.services.BinanceRestService

class BinanceClient {

    private val retrofit = RetrofitFactory.create()

    val rest by lazy {
        retrofit.create<BinanceRestService>()
    }
}