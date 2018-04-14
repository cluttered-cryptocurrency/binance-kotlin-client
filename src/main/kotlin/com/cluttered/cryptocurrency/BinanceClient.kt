package com.cluttered.cryptocurrency

import com.cluttered.cryptocurrency.retrofit.create
import com.cluttered.cryptocurrency.services.AccountService
import com.cluttered.cryptocurrency.services.UserDataStreamService

class BinanceClient(key: String, secret: String) : PublicBinanceClient(key, secret) {

    val account by lazy {
        retrofit.create<AccountService>()
    }

    val userDataStream by lazy {
        retrofit.create<UserDataStreamService>()
    }
}