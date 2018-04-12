package com.cluttered.cryptocurrency.services

import com.cluttered.cryptocurrency.BinanceConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER
import com.cluttered.cryptocurrency.model.account.*
import com.cluttered.cryptocurrency.model.account.OrderResponseType.RESULT
import io.reactivex.Observable
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query
import java.math.BigDecimal
import java.time.Instant
import java.util.concurrent.TimeUnit

interface AccountRestService {

    companion object {
        val ONE_MINUTE_IN_MILLIS = TimeUnit.MINUTES.toMillis(1)
    }

    @Headers(ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
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
            @Query("recvWindow") receivingWindow: Long = ONE_MINUTE_IN_MILLIS,
            @Query("timestamp") timestamp: Long = Instant.now().toEpochMilli())
            : Observable<OrderResult>

    @Headers(ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
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
            @Query("recvWindow") receivingWindow: Long = ONE_MINUTE_IN_MILLIS,
            @Query("timestamp") timestamp: Long = Instant.now().toEpochMilli())
            : Observable<OrderResult>

    /* ######## Buy by Type ######## */

    fun buyLimit(symbol: String, timeInForce: TimeInForce, quantity: BigDecimal, price: BigDecimal) = orderLimit(symbol, OrderSide.BUY, timeInForce, quantity, price)

    fun buyMarket(symbol: String, quantity: BigDecimal) = orderMarket(symbol, OrderSide.BUY, quantity)

    fun buyStopLoss(symbol: String, quantity: BigDecimal, stopPrice: BigDecimal) = orderStopLoss(symbol, OrderSide.BUY, quantity, stopPrice)

    fun buyStopLossLimit(symbol: String, timeInForce: TimeInForce, quantity: BigDecimal, price: BigDecimal, stopPrice: BigDecimal) = orderStopLossLimit(symbol, OrderSide.BUY, timeInForce, quantity, price, stopPrice)

    fun buyTakeProfit(symbol: String, quantity: BigDecimal, stopPrice: BigDecimal) = orderTakeProfit(symbol, OrderSide.BUY, quantity, stopPrice)

    fun buyTakeProfitLimit(symbol: String, timeInForce: TimeInForce, quantity: BigDecimal, price: BigDecimal, stopPrice: BigDecimal) = orderTakeProfitLimit(symbol, OrderSide.BUY, timeInForce, quantity, price, stopPrice)

    fun buyLimitMaker(symbol: String, quantity: BigDecimal, price: BigDecimal) = orderLimitMaker(symbol, OrderSide.BUY, quantity, price)

    /* ######## Sell by Type ######## */

    fun sellLimit(symbol: String, timeInForce: TimeInForce, quantity: BigDecimal, price: BigDecimal) = orderLimit(symbol, OrderSide.SELL, timeInForce, quantity, price)

    fun sellMarket(symbol: String, quantity: BigDecimal) = orderMarket(symbol, OrderSide.SELL, quantity)

    fun sellStopLoss(symbol: String, quantity: BigDecimal, stopPrice: BigDecimal) = orderStopLoss(symbol, OrderSide.SELL, quantity, stopPrice)

    fun sellStopLossLimit(symbol: String, timeInForce: TimeInForce, quantity: BigDecimal, price: BigDecimal, stopPrice: BigDecimal) = orderStopLossLimit(symbol, OrderSide.SELL, timeInForce, quantity, price, stopPrice)

    fun sellTakeProfit(symbol: String, quantity: BigDecimal, stopPrice: BigDecimal) = orderTakeProfit(symbol, OrderSide.SELL, quantity, stopPrice)

    fun sellTakeProfitLimit(symbol: String, timeInForce: TimeInForce, quantity: BigDecimal, price: BigDecimal, stopPrice: BigDecimal) = orderTakeProfitLimit(symbol, OrderSide.SELL, timeInForce, quantity, price, stopPrice)

    fun sellLimitMaker(symbol: String, quantity: BigDecimal, price: BigDecimal) = orderLimitMaker(symbol, OrderSide.SELL, quantity, price)

    /* ######## Order by Type ######## */

    private fun orderLimit(symbol: String, side: OrderSide, timeInForce: TimeInForce, quantity: BigDecimal, price: BigDecimal) = order(symbol, side, OrderType.LIMIT, timeInForce, quantity, price)

    private fun orderMarket(symbol: String, side: OrderSide, quantity: BigDecimal) = order(symbol, side, OrderType.MARKET, quantity = quantity)

    private fun orderStopLoss(symbol: String, side: OrderSide, quantity: BigDecimal, stopPrice: BigDecimal) = order(symbol, side, OrderType.STOP_LOSS, quantity = quantity, stopPrice = stopPrice)

    private fun orderStopLossLimit(symbol: String, side: OrderSide, timeInForce: TimeInForce, quantity: BigDecimal, price: BigDecimal, stopPrice: BigDecimal) = order(symbol, side, OrderType.STOP_LOSS_LIMIT, timeInForce, quantity, price, stopPrice = stopPrice)

    private fun orderTakeProfit(symbol: String, side: OrderSide, quantity: BigDecimal, stopPrice: BigDecimal) = order(symbol, side, OrderType.TAKE_PROFIT, quantity = quantity, stopPrice = stopPrice)

    private fun orderTakeProfitLimit(symbol: String, side: OrderSide, timeInForce: TimeInForce, quantity: BigDecimal, price: BigDecimal, stopPrice: BigDecimal) = order(symbol, side, OrderType.TAKE_PROFIT_LIMIT, timeInForce, quantity, price, stopPrice = stopPrice)

    private fun orderLimitMaker(symbol: String, side: OrderSide, quantity: BigDecimal, price: BigDecimal) = order(symbol, side, OrderType.LIMIT_MAKER, quantity = quantity, price = price)
}