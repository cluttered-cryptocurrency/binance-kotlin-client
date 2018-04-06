package com.cluttered.cryptocurrency.services

import com.cluttered.cryptocurrency.BinanceConstants.MINUTE_IN_MILLIS
import com.cluttered.cryptocurrency.model.account.*
import com.cluttered.cryptocurrency.model.account.OrderResponseType.RESULT
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Query
import java.math.BigDecimal
import java.time.Instant

interface AccountRestService {

    @POST("api/v3/order")
    fun order(
            @Query("symbol") symbol: String,
            @Query("side") side: OrderSide,
            @Query("type") type: OrderType,
            @Query("timeInForce") timeInForce: TimeInForce? = null,
            @Query("quantity") quantity: BigDecimal,
            @Query("price") price: BigDecimal? = null,
            @Query("newClientOrderId") orderId: String? = null,
            @Query("stopPrice") stopPrice: BigDecimal? = null,
            @Query("icebergQty") icebergQuantity: BigDecimal? = null,
            @Query("newOrderRespType") responseType: OrderResponseType = RESULT,
            @Query("recvWindow") receivingWindow: Long = MINUTE_IN_MILLIS,
            @Query("timestamp") timestamp: Long = Instant.now().toEpochMilli())
            : Observable<OrderResult>

    @POST("api/v3/order/test")
    fun orderTest(
            @Query("symbol") symbol: String,
            @Query("side") side: OrderSide,
            @Query("type") type: OrderType,
            @Query("timeInForce") timeInForce: TimeInForce? = null,
            @Query("quantity") quantity: BigDecimal,
            @Query("price") price: BigDecimal? = null,
            @Query("newClientOrderId") orderId: String? = null,
            @Query("stopPrice") stopPrice: BigDecimal? = null,
            @Query("icebergQty") icebergQuantity: BigDecimal? = null,
            @Query("newOrderRespType") responseType: OrderResponseType = RESULT,
            @Query("recvWindow") receivingWindow: Long = MINUTE_IN_MILLIS,
            @Query("timestamp") timestamp: Long = Instant.now().toEpochMilli())
            : Observable<OrderResult>

    fun orderLimit(symbol: String, side: OrderSide, timeInForce: TimeInForce, quantity: BigDecimal, price: BigDecimal)
            = order(symbol, side, OrderType.LIMIT, timeInForce, quantity, price)

    fun orderLimitMaker(symbol: String, side: OrderSide, quantity: BigDecimal, price: BigDecimal)
            = order(symbol, side, OrderType.LIMIT_MAKER, quantity=quantity, price=price)
}