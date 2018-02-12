package com.cluttered.cryptocurrency

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class PublicBinanceClientTest {

    private val client = PublicBinanceClient()

    @Test
    fun testPing() {
        client.public.ping()
                .test()
                .assertComplete()
    }

    @Test
    fun testTime() {
        val testObserver = client.public.time().test()

        println(testObserver.values()[0])

        testObserver.assertComplete()
        testObserver.assertValueCount(1)

        assertThat(testObserver.values()[0].serverTime).isNotNull()
    }

    @Test
    fun testExchangeInfo() {
        val testObserver = client.public.exchangeInfo().test()

        testObserver.assertComplete()
        testObserver.assertValueCount(1)

        val result = testObserver.values()
        println(result[0])

        assertThat(result[0].timezone).isEqualTo("UTC")
    }

//    @Test
//    fun testPingAsync() {
//        val testObserver = TestObserver<Response<Void>>()
//
//        client.public.ping()
//                .subscribeOn(Schedulers.newThread())
//                .subscribe(testObserver)
//
//        testObserver.await(2, SECONDS)
//
//        testObserver.assertComplete()
//        testObserver.assertValueCount(1)
//
//        assertThat(testObserver.values()[0].code()).isEqualTo(HTTP_OK)
//    }
}