package com.cluttered.cryptocurrency.ws

import com.cluttered.cryptocurrency.BinanceConstants.BASE_WEB_SOCKET_URL
import com.cluttered.cryptocurrency.model.marketdata.CandlestickInterval
import com.cluttered.cryptocurrency.model.ws.AggregateTradeEvent
import com.cluttered.cryptocurrency.model.ws.CandlestickEvent
import com.cluttered.cryptocurrency.model.ws.StreamEvent
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

    private var observableWebsocket: Observable<RxWebSocketEvent>? = null

    @Suppress("UNCHECKED_CAST")
    fun aggregateTrade(symbol: String): PublishSubject<AggregateTradeEvent> {
        val streamName = "${symbol.toLowerCase()}@aggTrade"
        println("initialize stream: $streamName")

        return subjectsByStreamName.getOrPut(streamName) {
            PublishSubject.create<AggregateTradeEvent>() as PublishSubject<Any>
        } as PublishSubject<AggregateTradeEvent>
    }

    @Suppress("UNCHECKED_CAST")
    fun candlestick(symbol: String, interval: CandlestickInterval): PublishSubject<CandlestickEvent> {
        val streamName = "${symbol.toLowerCase()}@kline_$interval"
        println("initialize stream: $streamName")

        return subjectsByStreamName.getOrPut(streamName) {
            PublishSubject.create<CandlestickEvent>() as PublishSubject<Any>
        } as PublishSubject<CandlestickEvent>
    }

    @Synchronized fun start(): Observable<RxWebSocketEvent> {
        if (observableWebsocket == null) {

            val requestUrl = "$BASE_WEB_SOCKET_URL/stream?streams=${subjectsByStreamName.keys.joinToString("/")}"
            println("Starting socket: $requestUrl")

            val request = Request.Builder()
                    .url(requestUrl)
                    .build()

            observableWebsocket = Observable.create {
                okHttpClient.newWebSocket(request, object : WebSocketListener() {
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

                        if (event.stream.contains("@kline")) {
                            return gson.fromJson<CandlestickEvent>(event.data)
                        }

                        return null
                    }
                })
            }
        }

        observableWebsocket!!.subscribe {  } // users aren't required to subscribe

        return observableWebsocket!!
    }
}