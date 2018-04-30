package com.cluttered.cryptocurrency.serial

import com.cluttered.cryptocurrency.model.withdraw.SystemStatus
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

class SystemStatusDeserializer : JsonDeserializer<SystemStatus> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): SystemStatus {
        return SystemStatus.fromOrdinal(json.asInt)
    }
}