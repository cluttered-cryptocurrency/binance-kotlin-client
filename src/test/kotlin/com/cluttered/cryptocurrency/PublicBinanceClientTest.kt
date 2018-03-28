package com.cluttered.cryptocurrency

import com.cluttered.cryptocurrency.TestHelpers.getJson
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.time.Instant

class PublicBinanceClientTest {

    private lateinit var publicBinanceClient: PublicBinanceClient
    private lateinit var mockServer: MockWebServer

    @Before
    @Throws
    fun setUp() {
        mockServer = MockWebServer()
        mockServer.start()

        BinanceConstants.BASE_URL = mockServer.url("").toString()
        publicBinanceClient = PublicBinanceClient()
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

        val testObserver = publicBinanceClient.public.ping().test()

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

        val testObserver = publicBinanceClient.public.time().test()

        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        assertThat(testObserver.values()[0].serverTime).isEqualTo(Instant.ofEpochMilli(1499827319559))

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

        val testObserver = publicBinanceClient.public.exchangeInfo().test()

        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        assertThat(testObserver.values()[0].serverTime).isEqualTo(Instant.ofEpochMilli(1508631584636))

        val request = mockServer.takeRequest()
        assertThat(request.path).isEqualTo(path)
    }

    @Test
    fun testDepth() {
        val path = "/api/v1/depth?symbol=ETHBTC&limit=100"

        mockServer.enqueue(
                MockResponse()
                        .setResponseCode(200)
                        .addHeader("Content-Type", "application/json")
                        .setBody(getJson("json/depth.json")))

        val testObserver = publicBinanceClient.public.depth("ETHBTC").test()

        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)

        assertThat(testObserver.values()[0].lastUpdateId).isEqualTo(1027024)

        val request = mockServer.takeRequest()
        assertThat(request.path).isEqualTo(path)
    }
}
