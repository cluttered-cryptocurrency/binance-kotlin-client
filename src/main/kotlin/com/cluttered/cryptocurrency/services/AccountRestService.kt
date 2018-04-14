package com.cluttered.cryptocurrency.services

import com.cluttered.cryptocurrency.BinanceConstants.ENDPOINT_SECURITY_TYPE_SIGNED_HEADER
import com.cluttered.cryptocurrency.model.account.*
import com.cluttered.cryptocurrency.model.account.OrderResponseType.RESULT
import com.cluttered.cryptocurrency.model.account.OrderSide.BUY
import com.cluttered.cryptocurrency.model.account.OrderSide.SELL
import com.cluttered.cryptocurrency.model.account.OrderType.*
import io.reactivex.Observable
import retrofit2.http.*
import java.math.BigDecimal
import java.time.Instant
import java.util.concurrent.TimeUnit.MINUTES

interface AccountRestService {

    companion object {
        val ONE_MINUTE_IN_MILLIS = MINUTES.toMillis(1)
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

    @Headers(ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("api/v3/order")
    fun getOrderById(
            @Query("symbol") symbol: String,
            @Query("orderId") orderId: Long,
            @Query("recvWindow") receivingWindow: Long = ONE_MINUTE_IN_MILLIS,
            @Query("timestamp") timestamp: Long = Instant.now().toEpochMilli())

    @Headers(ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("api/v3/order")
    fun getOrderByClientId(
            @Query("symbol") symbol: String,
            @Query("origClientOrderId") clientOrderId: String,
            @Query("recvWindow") receivingWindow: Long = ONE_MINUTE_IN_MILLIS,
            @Query("timestamp") timestamp: Long = Instant.now().toEpochMilli())
            : Observable<Order>

    @Headers(ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @DELETE("api/v3/order")
    fun cancelOrderById(
            @Query("symbol") symbol: String,
            @Query("orderId") orderId: Long,
            @Query("newClientOrderId") newClientOrderId: String? = null,
            @Query("recvWindow") receivingWindow: Long = ONE_MINUTE_IN_MILLIS,
            @Query("timestamp") timestamp: Long = Instant.now().toEpochMilli())
            : Observable<CancelledOrder>

    @Headers(ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @DELETE("api/v3/order")
    fun cancelOrderByClientId(
            @Query("symbol") symbol: String,
            @Query("origClientOrderId") clientOrderId: String,
            @Query("newClientOrderId") newClientOrderId: String? = null,
            @Query("recvWindow") receivingWindow: Long = ONE_MINUTE_IN_MILLIS,
            @Query("timestamp") timestamp: Long = Instant.now().toEpochMilli())
            : Observable<CancelledOrder>

    @Headers(ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("api/v3/openOrders")
    fun openOrders(
            @Query("symbol") symbol: String? = null,
            @Query("recvWindow") receivingWindow: Long = ONE_MINUTE_IN_MILLIS,
            @Query("timestamp") timestamp: Long = Instant.now().toEpochMilli())
            : Observable<MutableList<Order>>

    @Headers(ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("api/v3/allOrders")
    fun allOrders(
            @Query("symbol") symbol: String,
            @Query("orderId") orderId: Long? = null,
            @Query("limit") limit: Int? = null,
            @Query("recvWindow") receivingWindow: Long = ONE_MINUTE_IN_MILLIS,
            @Query("timestamp") timestamp: Long = Instant.now().toEpochMilli())
            : Observable<MutableList<Order>>

    @Headers(ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("api/v3/account")
    fun accountSnapshot(
            @Query("recvWindow") receivingWindow: Long = ONE_MINUTE_IN_MILLIS,
            @Query("timestamp") timestamp: Long = Instant.now().toEpochMilli())
            : Observable<AccountSnapshot>

    @Headers(ENDPOINT_SECURITY_TYPE_SIGNED_HEADER)
    @GET("api/v3/myTrades")
    fun myTrades(
            @Query("symbol") symbol: String,
            @Query("limit") limit: Int? = null,
            @Query("fromId") fromId: Long? = null,
            @Query("recvWindow") receivingWindow: Long = ONE_MINUTE_IN_MILLIS,
            @Query("timestamp") timestamp: Long = Instant.now().toEpochMilli())
            : Observable<MutableList<Trade>>

    // TODO: userDataStream

    /* ######## Buy by Type ######## */

    fun buyLimit(symbol: String, timeInForce: TimeInForce, quantity: BigDecimal, price: BigDecimal) = orderLimit(symbol, BUY, timeInForce, quantity, price)

    fun buyMarket(symbol: String, quantity: BigDecimal) = orderMarket(symbol, BUY, quantity)

    fun buyStopLoss(symbol: String, quantity: BigDecimal, stopPrice: BigDecimal) = orderStopLoss(symbol, BUY, quantity, stopPrice)

    fun buyStopLossLimit(symbol: String, timeInForce: TimeInForce, quantity: BigDecimal, price: BigDecimal, stopPrice: BigDecimal) = orderStopLossLimit(symbol, BUY, timeInForce, quantity, price, stopPrice)

    fun buyTakeProfit(symbol: String, quantity: BigDecimal, stopPrice: BigDecimal) = orderTakeProfit(symbol, BUY, quantity, stopPrice)

    fun buyTakeProfitLimit(symbol: String, timeInForce: TimeInForce, quantity: BigDecimal, price: BigDecimal, stopPrice: BigDecimal) = orderTakeProfitLimit(symbol, BUY, timeInForce, quantity, price, stopPrice)

    fun buyLimitMaker(symbol: String, quantity: BigDecimal, price: BigDecimal) = orderLimitMaker(symbol, BUY, quantity, price)

    /* ######## Sell by Type ######## */

    fun sellLimit(symbol: String, timeInForce: TimeInForce, quantity: BigDecimal, price: BigDecimal) = orderLimit(symbol, SELL, timeInForce, quantity, price)

    fun sellMarket(symbol: String, quantity: BigDecimal) = orderMarket(symbol, SELL, quantity)

    fun sellStopLoss(symbol: String, quantity: BigDecimal, stopPrice: BigDecimal) = orderStopLoss(symbol, SELL, quantity, stopPrice)

    fun sellStopLossLimit(symbol: String, timeInForce: TimeInForce, quantity: BigDecimal, price: BigDecimal, stopPrice: BigDecimal) = orderStopLossLimit(symbol, SELL, timeInForce, quantity, price, stopPrice)

    fun sellTakeProfit(symbol: String, quantity: BigDecimal, stopPrice: BigDecimal) = orderTakeProfit(symbol, SELL, quantity, stopPrice)

    fun sellTakeProfitLimit(symbol: String, timeInForce: TimeInForce, quantity: BigDecimal, price: BigDecimal, stopPrice: BigDecimal) = orderTakeProfitLimit(symbol, SELL, timeInForce, quantity, price, stopPrice)

    fun sellLimitMaker(symbol: String, quantity: BigDecimal, price: BigDecimal) = orderLimitMaker(symbol, SELL, quantity, price)

    /* ######## Order by Type ######## */

    private fun orderLimit(symbol: String, side: OrderSide, timeInForce: TimeInForce, quantity: BigDecimal, price: BigDecimal) = order(symbol, side, LIMIT, timeInForce, quantity, price)

    private fun orderMarket(symbol: String, side: OrderSide, quantity: BigDecimal) = order(symbol, side, MARKET, quantity = quantity)

    private fun orderStopLoss(symbol: String, side: OrderSide, quantity: BigDecimal, stopPrice: BigDecimal) = order(symbol, side, STOP_LOSS, quantity = quantity, stopPrice = stopPrice)

    private fun orderStopLossLimit(symbol: String, side: OrderSide, timeInForce: TimeInForce, quantity: BigDecimal, price: BigDecimal, stopPrice: BigDecimal) = order(symbol, side, STOP_LOSS_LIMIT, timeInForce, quantity, price, stopPrice = stopPrice)

    private fun orderTakeProfit(symbol: String, side: OrderSide, quantity: BigDecimal, stopPrice: BigDecimal) = order(symbol, side, TAKE_PROFIT, quantity = quantity, stopPrice = stopPrice)

    private fun orderTakeProfitLimit(symbol: String, side: OrderSide, timeInForce: TimeInForce, quantity: BigDecimal, price: BigDecimal, stopPrice: BigDecimal) = order(symbol, side, TAKE_PROFIT_LIMIT, timeInForce, quantity, price, stopPrice = stopPrice)

    private fun orderLimitMaker(symbol: String, side: OrderSide, quantity: BigDecimal, price: BigDecimal) = order(symbol, side, LIMIT_MAKER, quantity = quantity, price = price)

    /* ######## Test Buy by Type ######## */

    fun buyLimitTest(symbol: String, timeInForce: TimeInForce, quantity: BigDecimal, price: BigDecimal) = orderLimitTest(symbol, BUY, timeInForce, quantity, price)

    fun buyMarketTest(symbol: String, quantity: BigDecimal) = orderMarketTest(symbol, BUY, quantity)

    fun buyStopLossTest(symbol: String, quantity: BigDecimal, stopPrice: BigDecimal) = orderStopLossTest(symbol, BUY, quantity, stopPrice)

    fun buyStopLossLimitTest(symbol: String, timeInForce: TimeInForce, quantity: BigDecimal, price: BigDecimal, stopPrice: BigDecimal) = orderStopLossLimitTest(symbol, BUY, timeInForce, quantity, price, stopPrice)

    fun buyTakeProfitTest(symbol: String, quantity: BigDecimal, stopPrice: BigDecimal) = orderTakeProfitTest(symbol, BUY, quantity, stopPrice)

    fun buyTakeProfitLimitTest(symbol: String, timeInForce: TimeInForce, quantity: BigDecimal, price: BigDecimal, stopPrice: BigDecimal) = orderTakeProfitLimitTest(symbol, BUY, timeInForce, quantity, price, stopPrice)

    fun buyLimitMakerTest(symbol: String, quantity: BigDecimal, price: BigDecimal) = orderLimitMakerTest(symbol, BUY, quantity, price)

    /* ######## Test Sell by Type ######## */

    fun sellLimitTest(symbol: String, timeInForce: TimeInForce, quantity: BigDecimal, price: BigDecimal) = orderLimitTest(symbol, SELL, timeInForce, quantity, price)

    fun sellMarketTest(symbol: String, quantity: BigDecimal) = orderMarketTest(symbol, SELL, quantity)

    fun sellStopLossTest(symbol: String, quantity: BigDecimal, stopPrice: BigDecimal) = orderStopLossTest(symbol, SELL, quantity, stopPrice)

    fun sellStopLossLimitTest(symbol: String, timeInForce: TimeInForce, quantity: BigDecimal, price: BigDecimal, stopPrice: BigDecimal) = orderStopLossLimitTest(symbol, SELL, timeInForce, quantity, price, stopPrice)

    fun sellTakeProfitTest(symbol: String, quantity: BigDecimal, stopPrice: BigDecimal) = orderTakeProfitTest(symbol, SELL, quantity, stopPrice)

    fun sellTakeProfitLimitTest(symbol: String, timeInForce: TimeInForce, quantity: BigDecimal, price: BigDecimal, stopPrice: BigDecimal) = orderTakeProfitLimitTest(symbol, SELL, timeInForce, quantity, price, stopPrice)

    fun sellLimitMakerTest(symbol: String, quantity: BigDecimal, price: BigDecimal) = orderLimitMakerTest(symbol, SELL, quantity, price)

    /* ######## Test Order by Type ######## */

    private fun orderLimitTest(symbol: String, side: OrderSide, timeInForce: TimeInForce, quantity: BigDecimal, price: BigDecimal) = orderTest(symbol, side, LIMIT, timeInForce, quantity, price)

    private fun orderMarketTest(symbol: String, side: OrderSide, quantity: BigDecimal) = orderTest(symbol, side, MARKET, quantity = quantity)

    private fun orderStopLossTest(symbol: String, side: OrderSide, quantity: BigDecimal, stopPrice: BigDecimal) = orderTest(symbol, side, STOP_LOSS, quantity = quantity, stopPrice = stopPrice)

    private fun orderStopLossLimitTest(symbol: String, side: OrderSide, timeInForce: TimeInForce, quantity: BigDecimal, price: BigDecimal, stopPrice: BigDecimal) = orderTest(symbol, side, STOP_LOSS_LIMIT, timeInForce, quantity, price, stopPrice = stopPrice)

    private fun orderTakeProfitTest(symbol: String, side: OrderSide, quantity: BigDecimal, stopPrice: BigDecimal) = orderTest(symbol, side, TAKE_PROFIT, quantity = quantity, stopPrice = stopPrice)

    private fun orderTakeProfitLimitTest(symbol: String, side: OrderSide, timeInForce: TimeInForce, quantity: BigDecimal, price: BigDecimal, stopPrice: BigDecimal) = orderTest(symbol, side, TAKE_PROFIT_LIMIT, timeInForce, quantity, price, stopPrice = stopPrice)

    private fun orderLimitMakerTest(symbol: String, side: OrderSide, quantity: BigDecimal, price: BigDecimal) = orderTest(symbol, side, LIMIT_MAKER, quantity = quantity, price = price)
}