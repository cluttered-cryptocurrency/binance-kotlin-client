package com.cluttered.cryptocurrency.security

import com.cluttered.cryptocurrency.BinanceConstants.API_KEY_HEADER
import com.cluttered.cryptocurrency.BinanceConstants.ENDPOINT_SECURITY_TYPE_APIKEY
import com.cluttered.cryptocurrency.BinanceConstants.ENDPOINT_SECURITY_TYPE_SIGNED
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * A request interceptor that injects the API Key Header into requests, and signs messages, whenever required.
 */
class AuthenticationInterceptor(private val apiKey: String, secret: String) : Interceptor {

    private val hmacSha256 = HmacSHA256(secret)

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val newRequestBuilder = original.newBuilder()

        val isApiKeyRequired = original.header(ENDPOINT_SECURITY_TYPE_APIKEY) != null
        val isSignatureRequired = original.header(ENDPOINT_SECURITY_TYPE_SIGNED) != null
        newRequestBuilder.removeHeader(ENDPOINT_SECURITY_TYPE_SIGNED)
                .removeHeader(ENDPOINT_SECURITY_TYPE_SIGNED)

        // Endpoint requires sending a valid API-KEY
        if (isApiKeyRequired || isSignatureRequired) {
            newRequestBuilder.addHeader(API_KEY_HEADER, apiKey)
        }

        // Endpoint requires signing the payload
        if (isSignatureRequired) {
            val payload = original.url().query()
            if (payload != null && payload.isNotBlank()) {
                val signature = hmacSha256.encode(payload)
                val signedUrl = original.url().newBuilder().addQueryParameter("signature", signature).build()
                newRequestBuilder.url(signedUrl)
            }
        }

        // Build new request after adding the necessary authentication information
        val newRequest = newRequestBuilder.build()
        return chain.proceed(newRequest)
    }
}