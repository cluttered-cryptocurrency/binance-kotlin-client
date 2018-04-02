package com.cluttered.cryptocurrency.serial

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type
import java.time.Instant

class InstantDeserializer : JsonDeserializer<Instant> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Instant {
        val millis = json.asLong

        return Instant.ofEpochMilli(millis)
    }
}