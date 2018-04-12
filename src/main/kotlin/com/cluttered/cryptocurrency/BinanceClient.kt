package com.cluttered.cryptocurrency

import com.cluttered.cryptocurrency.retrofit.create
import com.cluttered.cryptocurrency.services.AccountRestService

class BinanceClient(key: String, secret: String) : PublicBinanceClient(key, secret) {

    val account by lazy {
        retrofit.create<AccountRestService>()
    }
}