package com.cluttered.cryptocurrency.retrofit

import com.cluttered.cryptocurrency.BinanceConstants.BASE_REST_URL
import com.cluttered.cryptocurrency.model.Candlestick
import com.cluttered.cryptocurrency.model.Depth
import com.cluttered.cryptocurrency.serial.CandlestickDeserializer
import com.cluttered.cryptocurrency.serial.DepthOrderDeserializer
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
                .baseUrl(BASE_REST_URL)
                .build()
    }

    @JvmStatic
    private fun createGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(
                GsonBuilder()
                        .registerTypeAdapter(Instant::class.java, InstantDeserializer())
                        .registerTypeAdapter(Depth.Order::class.java, DepthOrderDeserializer())
                        .registerTypeAdapter(Candlestick::class.java, CandlestickDeserializer())
                        .serializeNulls()
                        .create())
    }
}