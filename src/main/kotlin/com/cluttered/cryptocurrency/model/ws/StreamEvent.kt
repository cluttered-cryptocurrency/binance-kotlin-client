package com.cluttered.cryptocurrency.model.ws

import com.google.gson.JsonObject

data class StreamEvent(
        val stream: String,
        val data: JsonObject
)