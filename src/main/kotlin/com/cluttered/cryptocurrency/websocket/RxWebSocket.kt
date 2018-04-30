package com.cluttered.cryptocurrency.websocket

import com.cluttered.cryptocurrency.BinanceConstants.BASE_WEB_SOCKET_URL
import com.cluttered.cryptocurrency.model.marketdata.CandlestickInterval
import com.cluttered.cryptocurrency.model.marketdata.Depth
import com.cluttered.cryptocurrency.model.marketdata.DepthLevel
import com.cluttered.cryptocurrency.model.websocket.*
import com.google.gson.Gson
import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import okhttp3.*

object RxWebSocket {

    private inline fun <reified T> Gson.fromJson(json: String): T = fromJson(json, T::class.java)
    private inline fun <reified T> Gson.fromJson(json: JsonObject): T = fromJson(json, T::class.java)

    private val okHttpClient by lazy {
        OkHttpClient()
    }

    private val gson by lazy {
        Gson()
    }

    private val subjectsByStreamName: MutableMap<String, PublishSubject<Any>> by lazy {
        HashMap<String, PublishSubject<Any>>()
    }

    private var websocket: WebSocket? = null
    private var observableWebsocket: Observable<RxWebSocketEvent>? = null

    fun aggregateTrade(symbol: String): PublishSubject<AggregateTradeEvent> = initializeStream("${symbol.toLowerCase()}@aggTrade")

    fun trade(symbol: String): PublishSubject<TradeEvent> = initializeStream("${symbol.toLowerCase()}@trade")

    fun kline(symbol: String, interval: CandlestickInterval) = candlestick(symbol, interval)

    fun candlestick(symbol: String, interval: CandlestickInterval): PublishSubject<CandlestickEvent> = initializeStream("${symbol.toLowerCase()}@kline_$interval")

    fun ticker(symbol: String): PublishSubject<TickerEvent> = initializeStream("${symbol.toLowerCase()}@ticker")

    fun ticker(): PublishSubject<List<TickerEvent>> = initializeStream("!ticker@arr")

    fun depth(symbol: String, level: DepthLevel): PublishSubject<Depth> = initializeStream("${symbol.toLowerCase()}@depth$level")

    fun depthUpdate(symbol: String): PublishSubject<DepthUpdateEvent> = initializeStream("${symbol.toLowerCase()}@depth")

    @Suppress("UNCHECKED_CAST")
    private fun <T> initializeStream(streamName: String): PublishSubject<T> {
        println("initialize stream: $streamName")
        return subjectsByStreamName.getOrPut(streamName) {
            PublishSubject.create<T>() as PublishSubject<Any>
        } as PublishSubject<T>
    }

    @Synchronized
    fun start(): Observable<RxWebSocketEvent> {
        if (websocket == null && observableWebsocket == null) {

            val requestUrl = "$BASE_WEB_SOCKET_URL/stream?streams=${subjectsByStreamName.keys.joinToString("/")}"
            println("Starting socket: $requestUrl")

            val request = Request.Builder()
                    .url(requestUrl)
                    .build()

            observableWebsocket = Observable.create {
                websocket = okHttpClient.newWebSocket(request, object : WebSocketListener() {
                    override fun onOpen(webSocket: WebSocket, response: Response) {
                        it.onNext(RxWebSocketEvent.OpenEvent(webSocket))
                    }

                    override fun onFailure(webSocket: WebSocket, t: Throwable?, response: Response?) {
                        it.onNext(RxWebSocketEvent.FailureEvent(webSocket, t, response))
                        if (t != null) {
                            it.onError(t)
                        }
                    }

                    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                        it.onNext(RxWebSocketEvent.ClosingEvent(webSocket, code, reason))
                        it.onComplete()
                    }

                    override fun onMessage(webSocket: WebSocket, text: String) {
                        it.onNext(RxWebSocketEvent.MessageStringEvent(webSocket, text))

                        val event = gson.fromJson<StreamEvent>(text)
                        val eventData: Any? = parseEventData(event)
                        if (eventData != null) {
                            subjectsByStreamName[event.stream]!!.onNext(eventData)
                        }
                    }

                    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                        it.onNext(RxWebSocketEvent.ClosedEvent(webSocket, code, reason))
                        it.onComplete()
                    }

                    private fun parseEventData(event: StreamEvent): Any? {

                        if (event.stream.endsWith("@aggTrade")) {
                            return gson.fromJson<AggregateTradeEvent>(event.data)
                        }

                        if (event.stream.endsWith("@trade")) {
                            return gson.fromJson<TradeEvent>(event.data)
                        }

                        if (event.stream.endsWith("@ticker")) {
                            return gson.fromJson<TickerEvent>(event.data)
                        }

                        if (event.stream.startsWith("!ticker")) {
                            return gson.fromJson<List<TickerEvent>>(event.data)
                        }

                        if (event.stream.contains("@kline")) {
                            return gson.fromJson<CandlestickEvent>(event.data)
                        }

                        if (event.stream.endsWith("@depth")) {
                            return gson.fromJson<DepthUpdateEvent>(event.data)
                        }

                        if (event.stream.contains("@depth")) {
                            return gson.fromJson<Depth>(event.data)
                        }

                        return null
                    }
                })
            }
        }

        observableWebsocket!!.subscribe { } // so users aren't required to subscribe but can

        return observableWebsocket!!
    }

    @Synchronized
    fun stop() {
        if (websocket == null || observableWebsocket == null)
            return
        println("stopping socket")
        websocket!!.close(1000, null)
        okHttpClient.dispatcher().executorService().shutdown()
        subjectsByStreamName.clear()
        websocket = null
        observableWebsocket = null
    }
}