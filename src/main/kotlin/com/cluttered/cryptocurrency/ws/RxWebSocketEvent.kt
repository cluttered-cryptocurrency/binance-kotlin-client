package com.cluttered.cryptocurrency.ws

import okhttp3.Response
import okhttp3.WebSocket

sealed class RxWebSocketEvent {

    data class OpenEvent(val webSocket: WebSocket) : RxWebSocketEvent()

    data class FailureEvent(val webSocket: WebSocket, val throwable: Throwable?, val response: Response?) : RxWebSocketEvent()

    data class ClosingEvent(val webSocket: WebSocket, val code: Int, val reason: String) : RxWebSocketEvent()

    data class MessageStringEvent(val webSocket: WebSocket, val text: String) : RxWebSocketEvent()

    data class ClosedEvent(val webSocket: WebSocket, val code: Int, val reason: String) : RxWebSocketEvent()
}