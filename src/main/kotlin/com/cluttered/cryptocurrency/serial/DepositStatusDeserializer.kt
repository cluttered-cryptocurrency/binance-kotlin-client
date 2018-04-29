package com.cluttered.cryptocurrency.serial

import com.cluttered.cryptocurrency.model.withdraw.DepositStatus
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

class DepositStatusDeserializer : JsonDeserializer<DepositStatus> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): DepositStatus {
        return DepositStatus.fromOrdinal(json.asInt)
    }
}