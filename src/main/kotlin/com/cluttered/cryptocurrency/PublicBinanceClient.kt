package com.cluttered.cryptocurrency

import com.cluttered.cryptocurrency.retrofit.RetrofitFactory
import com.cluttered.cryptocurrency.retrofit.create
import com.cluttered.cryptocurrency.services.PublicService

class PublicBinanceClient {

    private val retrofit = RetrofitFactory.create()

    val public by lazy {
        retrofit.create<PublicService>()
    }
}