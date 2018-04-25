package com.cluttered.cryptocurrency.ws

import com.cluttered.cryptocurrency.BinanceConstants.BASE_WEB_SOCKET_URL
import com.cluttered.cryptocurrency.model.marketdata.CandlestickInterval
import com.cluttered.cryptocurrency.model.ws.CandlestickEvent
import com.cluttered.cryptocurrency.model.ws.MessageEvent
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
    fun candlestick(symbol: String, interval: CandlestickInterval): PublishSubject<CandlestickEvent> {
        val streamName = "$symbol@kline_$interval"
        return subjectsByStreamName.getOrPut(streamName) {
            PublishSubject.create<CandlestickEvent>() as PublishSubject<Any>
        } as PublishSubject<CandlestickEvent>
    }

    @Synchronized fun start(): Observable<RxWebSocketEvent> {
        if (observableWebsocket == null) {

            val requestUrl = "$BASE_WEB_SOCKET_URL/stream?streams=${subjectsByStreamName.keys.joinToString("/")}"

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
                        val event = gson.fromJson<MessageEvent>(text)
                        val eventData: Any? = parseEventData(event)
                        if (eventData != null) {
                            subjectsByStreamName[event.message]!!.onNext(eventData)
                        }

                        it.onNext(RxWebSocketEvent.MessageStringEvent(webSocket, text))
                    }

                    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                        it.onNext(RxWebSocketEvent.ClosedEvent(webSocket, code, reason))
                        it.onComplete()
                    }

                    private fun parseEventData(event: MessageEvent): Any? {
                        if (event.message.contains("@kline")) {
                            return gson.fromJson<CandlestickEvent>(event.data)
                        }

                        return null
                    }
                })
            }
        }

        return observableWebsocket!!
    }
}