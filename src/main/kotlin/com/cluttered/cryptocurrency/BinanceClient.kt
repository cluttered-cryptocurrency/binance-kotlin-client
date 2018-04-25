package com.cluttered.cryptocurrency

import com.cluttered.cryptocurrency.retrofit.create
import com.cluttered.cryptocurrency.services.AccountService
import com.cluttered.cryptocurrency.services.UserDataStreamService
import com.cluttered.cryptocurrency.ws.RxWebSocket

class BinanceClient private constructor(key: String, secret: String) : PublicBinanceClient(key, secret) {

    companion object {
        @JvmStatic
        fun create(key: String, secret: String): BinanceClient = BinanceClient(key, secret)
    }

    val account by lazy {
        retrofit.create<AccountService>()
    }

    val userDataStream by lazy {
        retrofit.create<UserDataStreamService>()
    }
}