package com.cluttered.cryptocurrency.serial

import com.cluttered.cryptocurrency.model.Depth
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type
import java.math.BigDecimal

class DepthOrderDeserializer : JsonDeserializer<Depth.Order> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Depth.Order {
        val array = json.asJsonArray
        val price = BigDecimal(array[0].asString)
        val quantity = BigDecimal(array[1].asString)
        return Depth.Order(price, quantity)
    }
}