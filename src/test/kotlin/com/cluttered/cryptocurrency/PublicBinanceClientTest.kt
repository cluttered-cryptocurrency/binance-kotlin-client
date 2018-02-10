package com.cluttered.cryptocurrency

import org.junit.Test


class PublicBinanceClientTest {

    private val client = PublicBinanceClient()

    @Test
    fun testPing() {
        client.public.pingCompletable()
                .test()
                .assertComplete()
    }

//    @Test
//    fun testPing() {
//        val testObserver = TestObserver<Response<Void>>()
//
//        client.public.ping().subscribe(testObserver)
//
//        testObserver.assertComplete()
//        testObserver.assertValueCount(1)
//
//        assertThat(testObserver.values()[0].code()).isEqualTo(HTTP_OK)
//    }
//
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