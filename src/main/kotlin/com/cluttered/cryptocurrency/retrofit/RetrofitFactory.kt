package com.cluttered.cryptocurrency.retrofit

import com.cluttered.cryptocurrency.BinanceConstants.BASE_REST_URL
import com.cluttered.cryptocurrency.model.marketdata.Candlestick
import com.cluttered.cryptocurrency.model.marketdata.Depth
import com.cluttered.cryptocurrency.security.AuthenticationInterceptor
import com.cluttered.cryptocurrency.serial.CandlestickDeserializer
import com.cluttered.cryptocurrency.serial.DepthOrderDeserializer
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

inline fun <reified T> Retrofit.create(): T = create(T::class.java)

object RetrofitFactory {

    @JvmStatic
    fun create(key: String, secret: String): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(createGsonConverterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(createOkHttpClient(key, secret))
                .baseUrl(BASE_REST_URL)
                .build()
    }

    @JvmStatic
    private fun createGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(
                GsonBuilder()
                        .registerTypeAdapter(Depth.Order::class.java, DepthOrderDeserializer())
                        .registerTypeAdapter(Candlestick::class.java, CandlestickDeserializer())
                        .create())
    }

    @JvmStatic
    private fun createOkHttpClient(key: String, secret: String): OkHttpClient {
        val builder = OkHttpClient.Builder()

        if (key.isNotBlank() && secret.isNotBlank()) {
            builder.addNetworkInterceptor(AuthenticationInterceptor(key, secret))
        }

        return builder.build()
    }
}