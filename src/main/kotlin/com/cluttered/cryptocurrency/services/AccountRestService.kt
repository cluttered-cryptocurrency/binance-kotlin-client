package com.cluttered.cryptocurrency.services

import com.cluttered.cryptocurrency.BinanceConstants.RECEIVING_WINDOW_1_MINUTE
import com.cluttered.cryptocurrency.model.account.*
import com.cluttered.cryptocurrency.model.account.OrderResponseType.RESULT
import io.reactivex.Observer
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
            @Query("recvWindow") receivingWindow: Long = RECEIVING_WINDOW_1_MINUTE,
            @Query("timestamp") timestamp: Long = Instant.now().toEpochMilli())
            : Observer<OrderResult>

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
            @Query("recvWindow") receivingWindow: Long = RECEIVING_WINDOW_1_MINUTE,
            @Query("timestamp") timestamp: Long = Instant.now().toEpochMilli())
            : Observer<OrderResult>
}