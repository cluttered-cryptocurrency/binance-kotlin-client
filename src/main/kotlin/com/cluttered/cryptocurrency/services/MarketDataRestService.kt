package com.cluttered.cryptocurrency.services

import com.cluttered.cryptocurrency.model.marketdata.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MarketDataRestService {

    @GET("api/v1/depth")
    fun depth(
            @Query("symbol") symbol: String,
            @Query("limit") limit: Limit? = null)
            : Observable<Depth>

    @GET("api/v1/trades")
    fun trades(
            @Query("symbol") symbol: String,
            @Query("limit") limit: Int? = null)
            : Observable<MutableList<Trade>>

    @GET("api/v1/historicalTrades")
    fun historicalTrades(
            @Query("symbol") symbol: String,
            @Query("limit") limit: Int? = null,
            @Query("fromId") fromId: Long? = null)
            : Observable<MutableList<Trade>>

    @GET("api/v1/aggTrades")
    fun aggregateTrades(
            @Query("symbol") symbol: String,
            @Query("fromId") fromId: Long? = null,
            @Query("startTime") startTime: Long? = null,
            @Query("endTime") endTime: Long? = null,
            @Query("limit") limit: Long? = null)
            : Observable<MutableList<AggregateTrade>>

    @GET("api/v1/klines")
    fun candlesticks(
            @Query("symbol") symbol: String,
            @Query("interval") interval: ChartInterval,
            @Query("limit") limit: Int? = null,
            @Query("startTime") startTime: Long? = null,
            @Query("endTime") endTime: Long? = null)
            : Observable<MutableList<Candlestick>>

    @GET("api/v1/ticker/24hr")
    fun ticker24HourAll(): Observable<MutableList<Ticker24Hour>>

    @GET("api/v1/ticker/24hr")
    fun ticker24Hour(@Query("symbol") symbol: String): Observable<Ticker24Hour>

    @GET("api/v3/ticker/price")
    fun tickerPriceAll(): Observable<MutableList<TickerPrice>>

    @GET("api/v3/ticker/price")
    fun tickerPrice(@Query("symbol") symbol: String): Observable<TickerPrice>

    @GET("api/v3/ticker/bookTicker")
    fun tickerBookAll(): Observable<MutableList<TickerBook>>

    @GET("api/v3/ticker/bookTicker")
    fun tickerBook(@Query("symbol") symbol: String): Observable<TickerBook>
}