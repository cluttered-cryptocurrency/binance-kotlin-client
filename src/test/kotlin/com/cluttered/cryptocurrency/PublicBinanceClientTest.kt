package com.cluttered.cryptocurrency

import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import retrofit2.Response
import java.net.HttpURLConnection.HTTP_OK
import java.util.concurrent.TimeUnit.SECONDS


class PublicBinanceClientTest {

    private val client = PublicBinanceClient()

    @Test
    fun testPing() {
        val testObserver = TestObserver<Response<Void>>()

        client.public.ping().subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertValueCount(1)

        assertThat(testObserver.values()[0].code()).isEqualTo(HTTP_OK)
    }

    @Test
    fun testPingAsync() {
        val testObserver = TestObserver<Response<Void>>()

        client.public.ping()
                .subscribeOn(Schedulers.newThread())
                .subscribe(testObserver)

        testObserver.await(2, SECONDS)

        testObserver.assertComplete()
        testObserver.assertValueCount(1)

        assertThat(testObserver.values()[0].code()).isEqualTo(HTTP_OK)
    }
}