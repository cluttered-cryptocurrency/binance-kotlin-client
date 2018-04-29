package com.cluttered.cryptocurrency.model.websocket

import com.google.gson.JsonObject

data class StreamEvent(
        val stream: String,
        val data: JsonObject
)