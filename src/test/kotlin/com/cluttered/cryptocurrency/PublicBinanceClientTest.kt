package com.cluttered.cryptocurrency

import com.cluttered.cryptocurrency.TestHelpers.getJson
import com.cluttered.cryptocurrency.model.marketdata.CandlestickInterval.MINUTES_5
import com.cluttered.cryptocurrency.model.marketdata.Limit.ONE_HUNDRED
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.math.BigDecimal

@RunWith(JUnit4::class)
class PublicBinanceClientTest {

    private lateinit var publicBinanceClient: PublicBinanceClient
    private lateinit var mockServer: MockWebServer

    @Before
    @Throws
    fun setUp() {
        mockServer = MockWebServer()
        mockServer.start()

        BinanceConstants.BASE_REST_URL = mockServer.url("").toString()
        publicBinanceClient = PublicBinanceClient.create()
    }

    @After
    @Throws
    fun tearDown() {
        mockServer.shutdown()
    }

    @Test
    fun testPing() {
        val path = "/api/v1/ping"

        mockServer.enqueue(
                MockResponse()
                        .setResponseCode(200)
                        .addHeader("Content-Type", "application/json")
                        .setBody("{}"))

        val testObserver = publicBinanceClient.general.ping().test()

        testObserver.assertNoErrors()
        testObserver.assertValueCount(0)
        testObserver.assertComplete()

        val request = mockServer.takeRequest()
        assertThat(request.path).isEqualTo(path)
    }

    @Test
    fun testTime() {
        val path = "/api/v1/time"

        mockServer.enqueue(
                MockResponse()
                        .setResponseCode(200)
                        .addHeader("Content-Type", "application/json")
                        .setBody(getJson("json/time.json")))

        val testObserver = publicBinanceClient.general.time().test()

        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        assertThat(testObserver.values()[0].serverTime).isEqualTo(1499827319559)

        val request = mockServer.takeRequest()
        assertThat(request.path).isEqualTo(path)
    }

    @Test
    fun testExchangeInfo() {
        val path = "/api/v1/exchangeInfo"

        mockServer.enqueue(
                MockResponse()
                        .setResponseCode(200)
                        .addHeader("Content-Type", "application/json")
                        .setBody(getJson("json/exchangeInfo.json")))

        val testObserver = publicBinanceClient.general.exchangeInfo().test()

        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        assertThat(testObserver.values()[0].serverTime).isEqualTo(1508631584636)

        val request = mockServer.takeRequest()
        assertThat(request.path).isEqualTo(path)
    }

    @Test
    fun testDepth() {
        val path = "/api/v1/depth?symbol=ETHBTC"

        mockServer.enqueue(
                MockResponse()
                        .setResponseCode(200)
                        .addHeader("Content-Type", "application/json")
                        .setBody(getJson("json/depth.json")))

        val testObserver = publicBinanceClient.marketData.depth("ETHBTC").test()

        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        assertThat(testObserver.values()[0].lastUpdateId).isEqualTo(1027024)

        val request = mockServer.takeRequest()
        assertThat(request.path).isEqualTo(path)
    }

    @Test
    fun testDepthLimits() {
        val path = "/api/v1/depth?symbol=ETHBTC&limit=100"

        mockServer.enqueue(
                MockResponse()
                        .setResponseCode(200)
                        .addHeader("Content-Type", "application/json")
                        .setBody(getJson("json/depth.json")))

        val testObserver = publicBinanceClient.marketData.depth("ETHBTC", ONE_HUNDRED).test()

        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        assertThat(testObserver.values()[0].lastUpdateId).isEqualTo(1027024)

        val request = mockServer.takeRequest()
        assertThat(request.path).isEqualTo(path)
    }

    @Test
    fun testTrades() {
        val path = "/api/v1/trades?symbol=ETHBTC"

        mockServer.enqueue(
                MockResponse()
                        .setResponseCode(200)
                        .addHeader("Content-Type", "application/json")
                        .setBody(getJson("json/trades.json")))

        val testObserver = publicBinanceClient.marketData.trades("ETHBTC").test()

        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        assertThat(testObserver.values()[0][0].quantity).isEqualTo(BigDecimal("12.00000000"))

        val request = mockServer.takeRequest()
        assertThat(request.path).isEqualTo(path)
    }

    @Test
    fun testTradesLimits() {
        val path = "/api/v1/trades?symbol=ETHBTC&limit=250"

        mockServer.enqueue(
                MockResponse()
                        .setResponseCode(200)
                        .addHeader("Content-Type", "application/json")
                        .setBody(getJson("json/trades.json")))

        val testObserver = publicBinanceClient.marketData.trades("ETHBTC", 250).test()

        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        assertThat(testObserver.values()[0][0].quantity).isEqualTo(BigDecimal("12.00000000"))

        val request = mockServer.takeRequest()
        assertThat(request.path).isEqualTo(path)
    }

    @Test
    fun testHistoricalTrades() {
        val path = "/api/v1/historicalTrades?symbol=ETHBTC"

        mockServer.enqueue(
                MockResponse()
                        .setResponseCode(200)
                        .addHeader("Content-Type", "application/json")
                        .setBody(getJson("json/trades.json")))

        val testObserver = publicBinanceClient.marketData.historicalTrades("ETHBTC").test()

        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        assertThat(testObserver.values()[0][0].quantity).isEqualTo(BigDecimal("12.00000000"))

        val request = mockServer.takeRequest()
        assertThat(request.path).isEqualTo(path)
    }

    @Test
    fun testHistoricalTradesLimit() {
        val path = "/api/v1/historicalTrades?symbol=ETHBTC&limit=235"

        mockServer.enqueue(
                MockResponse()
                        .setResponseCode(200)
                        .addHeader("Content-Type", "application/json")
                        .setBody(getJson("json/trades.json")))

        val testObserver = publicBinanceClient.marketData.historicalTrades("ETHBTC", 235).test()

        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        assertThat(testObserver.values()[0][0].quantity).isEqualTo(BigDecimal("12.00000000"))

        val request = mockServer.takeRequest()
        assertThat(request.path).isEqualTo(path)
    }

    @Test
    fun testHistoricalTradesFromId() {
        val path = "/api/v1/historicalTrades?symbol=ETHBTC&fromId=6374"

        mockServer.enqueue(
                MockResponse()
                        .setResponseCode(200)
                        .addHeader("Content-Type", "application/json")
                        .setBody(getJson("json/trades.json")))

        val testObserver = publicBinanceClient.marketData.historicalTrades("ETHBTC", fromId = 6374).test()

        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        assertThat(testObserver.values()[0][0].quantity).isEqualTo(BigDecimal("12.00000000"))

        val request = mockServer.takeRequest()
        assertThat(request.path).isEqualTo(path)
    }

    @Test
    fun testHistoricalTradesLimitFromId() {
        val path = "/api/v1/historicalTrades?symbol=ETHBTC&limit=75&fromId=123456"

        mockServer.enqueue(
                MockResponse()
                        .setResponseCode(200)
                        .addHeader("Content-Type", "application/json")
                        .setBody(getJson("json/trades.json")))

        val testObserver = publicBinanceClient.marketData.historicalTrades("ETHBTC", 75, 123456).test()

        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        assertThat(testObserver.values()[0][0].quantity).isEqualTo(BigDecimal("12.00000000"))

        val request = mockServer.takeRequest()
        assertThat(request.path).isEqualTo(path)
    }

    @Test
    fun testAggregateTrades() {
        val path = "/api/v1/aggTrades?symbol=ETHBTC"

        mockServer.enqueue(
                MockResponse()
                        .setResponseCode(200)
                        .addHeader("Content-Type", "application/json")
                        .setBody(getJson("json/aggregateTrades.json")))

        val testObserver = publicBinanceClient.marketData.aggregateTrades("ETHBTC").test()

        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        assertThat(testObserver.values()[0][0].price).isEqualTo(BigDecimal("0.01633102"))

        val request = mockServer.takeRequest()
        assertThat(request.path).isEqualTo(path)
    }

    @Test
    fun testCandlesticks() {
        val path = "/api/v1/klines?symbol=ETHBTC&interval=5m"

        mockServer.enqueue(
                MockResponse()
                        .setResponseCode(200)
                        .addHeader("Content-Type", "application/json")
                        .setBody(getJson("json/candlesticks.json")))

        val testObserver = publicBinanceClient.marketData.candlesticks("ETHBTC", MINUTES_5).test()

        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        assertThat(testObserver.values()[0][0].high).isEqualTo(BigDecimal("0.80000000"))

        val request = mockServer.takeRequest()
        assertThat(request.path).isEqualTo(path)
    }

    @Test
    fun testTicker24HourList() {
        val path = "/api/v1/ticker/24hr"

        mockServer.enqueue(
                MockResponse()
                        .setResponseCode(200)
                        .addHeader("Content-Type", "application/json")
                        .setBody(getJson("json/ticker24HourList.json")))

        val testObserver = publicBinanceClient.marketData.ticker24HourAll().test()

        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        assertThat(testObserver.values()[0][0].askPrice).isEqualTo(BigDecimal("4.00000200"))

        val request = mockServer.takeRequest()
        assertThat(request.path).isEqualTo(path)
    }

    @Test
    fun testTicker24Hour() {
        val path = "/api/v1/ticker/24hr?symbol=ETHBTC"

        mockServer.enqueue(
                MockResponse()
                        .setResponseCode(200)
                        .addHeader("Content-Type", "application/json")
                        .setBody(getJson("json/ticker24Hour.json")))

        val testObserver = publicBinanceClient.marketData.ticker24Hour("ETHBTC").test()

        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        assertThat(testObserver.values()[0].bidPrice).isEqualTo(BigDecimal("4.00000000"))

        val request = mockServer.takeRequest()
        assertThat(request.path).isEqualTo(path)
    }

    @Test
    fun testTickerPriceList() {
        val path = "/api/v3/ticker/price"

        mockServer.enqueue(
                MockResponse()
                        .setResponseCode(200)
                        .addHeader("Content-Type", "application/json")
                        .setBody(getJson("json/tickerPriceList.json")))

        val testObserver = publicBinanceClient.marketData.tickerPriceAll().test()

        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        assertThat(testObserver.values()[0][1].price).isEqualTo(BigDecimal("0.07946600"))

        val request = mockServer.takeRequest()
        assertThat(request.path).isEqualTo(path)
    }

    @Test
    fun testTickerPrice() {
        val path = "/api/v3/ticker/price?symbol=LTCBTC"

        mockServer.enqueue(
                MockResponse()
                        .setResponseCode(200)
                        .addHeader("Content-Type", "application/json")
                        .setBody(getJson("json/tickerPrice.json")))

        val testObserver = publicBinanceClient.marketData.tickerPrice("LTCBTC").test()

        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        assertThat(testObserver.values()[0].price).isEqualTo(BigDecimal("4.00000200"))

        val request = mockServer.takeRequest()
        assertThat(request.path).isEqualTo(path)
    }

    @Test
    fun testTickerBookList() {
        val path = "/api/v3/ticker/bookTicker"

        mockServer.enqueue(
                MockResponse()
                        .setResponseCode(200)
                        .addHeader("Content-Type", "application/json")
                        .setBody(getJson("json/tickerBookList.json")))

        val testObserver = publicBinanceClient.marketData.tickerBookAll().test()

        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        assertThat(testObserver.values()[0][1].askPrice).isEqualTo(BigDecimal("100000.00000000"))

        val request = mockServer.takeRequest()
        assertThat(request.path).isEqualTo(path)
    }

    @Test
    fun testTickerBook() {
        val path = "/api/v3/ticker/bookTicker?symbol=LTCBTC"

        mockServer.enqueue(
                MockResponse()
                        .setResponseCode(200)
                        .addHeader("Content-Type", "application/json")
                        .setBody(getJson("json/tickerBook.json")))

        val testObserver = publicBinanceClient.marketData.tickerBook("LTCBTC").test()

        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        assertThat(testObserver.values()[0].askPrice).isEqualTo(BigDecimal("4.00000200"))

        val request = mockServer.takeRequest()
        assertThat(request.path).isEqualTo(path)
    }
}
