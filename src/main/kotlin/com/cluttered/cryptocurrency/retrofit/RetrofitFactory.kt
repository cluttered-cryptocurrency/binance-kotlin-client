package com.cluttered.cryptocurrency.retrofit

import com.cluttered.cryptocurrency.BinanceConstants.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

inline fun <reified T> Retrofit.create(): T = create(T::class.java)

object RetrofitFactory {

    @JvmStatic
    fun create(): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build()
    }
}