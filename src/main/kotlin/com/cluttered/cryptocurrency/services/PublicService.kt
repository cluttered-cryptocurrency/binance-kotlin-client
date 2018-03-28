package com.cluttered.cryptocurrency.services

import com.cluttered.cryptocurrency.model.Depth
import com.cluttered.cryptocurrency.model.ExchangeInfo
import com.cluttered.cryptocurrency.model.ServerTime
import com.cluttered.cryptocurrency.model.Trade
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface PublicService {

    companion object {
        const val API_V1: String = "api/v1"
    }

    @GET("$API_V1/ping")
    fun ping(): Completable

    @GET("$API_V1/time")
    fun time(): Observable<ServerTime>

    @GET("$API_V1/exchangeInfo")
    fun exchangeInfo(): Observable<ExchangeInfo>

    @GET("$API_V1/depth")
    fun depth(@Query("symbol") symbol: String, @Query("limit") limit: Int? = null): Observable<Depth>

    @GET("$API_V1/trades")
    fun trades(@Query("symbol") symbol: String, @Query("limit") limit: Int = 500): Observable<MutableList<Trade>>

    @GET("$API_V1/historicalTrades")
    fun historicalTrades(@Query("symbol") symbol: String, @Query("limit") limit: Int = 500): Observable<MutableList<Trade>>

    @GET("$API_V1/historicalTrades")
    fun historicalTrades(@Query("symbol") symbol: String, @Query("limit") limit: Int = 500, @Query("fromId") fromId: Long): Observable<MutableList<Trade>>
}