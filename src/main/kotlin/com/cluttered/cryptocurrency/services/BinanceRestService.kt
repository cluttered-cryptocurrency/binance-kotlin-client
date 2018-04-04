package com.cluttered.cryptocurrency.services

import com.cluttered.cryptocurrency.model.*
import com.cluttered.cryptocurrency.model.enum.ChartInterval
import com.cluttered.cryptocurrency.model.enum.Limit
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface BinanceRestService {

    companion object {
        const val API: String = "api"
        const val V1: String = "v1"
        const val V3: String = "v3"
    }

    /* ######## General ######## */

    @GET("$API/$V1/ping")
    fun ping(): Completable

    @GET("$API/$V1/time")
    fun time(): Observable<ServerTime>

    @GET("$API/$V1/exchangeInfo")
    fun exchangeInfo(): Observable<ExchangeInfo>

    /* ######## Market Data ######## */

    @GET("$API/$V1/depth")
    fun depth(
            @Query("symbol") symbol: String,
            @Query("limit") limit: Limit? = null)
            : Observable<Depth>

    @GET("$API/$V1/trades")
    fun trades(
            @Query("symbol") symbol: String,
            @Query("limit") limit: Int? = null)
            : Observable<MutableList<Trade>>

    @GET("$API/$V1/historicalTrades")
    fun historicalTrades(
            @Query("symbol") symbol: String,
            @Query("limit") limit: Int? = null,
            @Query("fromId") fromId: Long? = null)
            : Observable<MutableList<Trade>>

    @GET("$API/$V1/aggTrades")
    fun aggregateTrades(
            @Query("symbol") symbol: String,
            @Query("fromId") fromId: Long? = null,
            @Query("startTime") startTime: Long? = null,
            @Query("endTime") endTime: Long? = null,
            @Query("limit") limit: Long? = null)
            : Observable<MutableList<AggregateTrade>>

    @GET("$API/$V1/klines")
    fun candlesticks(
            @Query("symbol") symbol: String,
            @Query("interval") interval: ChartInterval,
            @Query("limit") limit: Int? = null,
            @Query("startTime") startTime: Long? = null,
            @Query("endTime") endTime: Long? = null)
            : Observable<MutableList<Candlestick>>

    @GET("$API/$V1/ticker/24hr")
    fun ticker24Hour(): Observable<MutableList<Ticker24Hour>>

    @GET("$API/$V1/ticker/24hr")
    fun ticker24Hour(@Query("symbol") symbol: String): Observable<Ticker24Hour>

    @GET("$API/$V3/ticker/price")
    fun tickerPrice(): Observable<MutableList<TickerPrice>>

    @GET("$API/$V3/ticker/price")
    fun tickerPrice(@Query("symbol") symbol: String): Observable<TickerPrice>

    @GET("$API/$V3/ticker/bookTicker")
    fun tickerBook(): Observable<MutableList<TickerBook>>

    @GET("$API/$V3/ticker/bookTicker")
    fun tickerBook(@Query("symbol") symbol: String): Observable<TickerBook>
}