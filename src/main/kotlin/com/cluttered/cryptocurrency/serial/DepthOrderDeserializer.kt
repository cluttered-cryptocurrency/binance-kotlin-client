package com.cluttered.cryptocurrency.serial

import com.cluttered.cryptocurrency.model.Depth
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

class DepthOrderDeserializer : JsonDeserializer<Depth.Order> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Depth.Order {
        val array = json.asJsonArray

        val price = array[0].asBigDecimal
        val quantity = array[1].asBigDecimal

        return Depth.Order(price, quantity)
    }
}