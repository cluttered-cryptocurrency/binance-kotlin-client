package com.cluttered.cryptocurrency.serial

import com.cluttered.cryptocurrency.model.marketdata.Candlestick
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

class CandlestickDeserializer : JsonDeserializer<Candlestick> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Candlestick {
        val array = json.asJsonArray

        val openTime = array[0].asLong
        val open = array[1].asBigDecimal
        val high = array[2].asBigDecimal
        val low = array[3].asBigDecimal
        val close = array[4].asBigDecimal
        val volume = array[5].asBigDecimal
        val closeTime = array[6].asLong
        val quoteAssetVolume = array[7].asBigDecimal
        val numberOfTrades = array[8].asLong
        val takerBuyBaseAssetVolume = array[9].asBigDecimal
        val takerBuyQuoteAssetVolume = array[10].asBigDecimal

        return Candlestick(openTime, open, high, low, close, volume, closeTime,
                quoteAssetVolume, numberOfTrades, takerBuyBaseAssetVolume, takerBuyQuoteAssetVolume)
    }
}