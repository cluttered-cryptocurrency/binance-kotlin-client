package com.cluttered.cryptocurrency.retrofit

import com.cluttered.cryptocurrency.BinanceConstants.BASE_URL
import com.cluttered.cryptocurrency.serial.InstantDeserializer
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Instant

inline fun <reified T> Retrofit.create(): T = create(T::class.java)

object RetrofitFactory {

    @JvmStatic
    fun create(): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(createGsonConverterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build()
    }

    @JvmStatic
    private fun createGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(
                GsonBuilder()
                        .registerTypeAdapter(Instant::class.java, InstantDeserializer())
                        .serializeNulls()
                        .create())
    }
}