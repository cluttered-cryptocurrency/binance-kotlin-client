package com.cluttered.cryptocurrency.model.ws

import com.google.gson.JsonObject

data class MessageEvent(
        val message: String,
        val data: JsonObject
)