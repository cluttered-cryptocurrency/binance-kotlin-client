package com.cluttered.cryptocurrency

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import retrofit2.Response
import java.net.HttpURLConnection.HTTP_OK

class PublicBinanceClientTest {

    private val client = PublicBinanceClient()

    @Test
    fun testPing() {
        var result: Response<Void>? = null
        client.public.ping()
                .subscribe({
                    println("Ping Response: $it")
                    result = it
                })

        assertThat(result?.code()).isEqualTo(HTTP_OK)
    }
}