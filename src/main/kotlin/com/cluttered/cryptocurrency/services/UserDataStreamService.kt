package com.cluttered.cryptocurrency.services

import com.cluttered.cryptocurrency.BinanceConstants.ENDPOINT_SECURITY_TYPE_APIKEY_HEADER
import com.cluttered.cryptocurrency.model.userstream.ListenKey
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.*

interface UserDataStreamService {

    @Headers(ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    @POST("api/v1/userDataStream")
    fun start(): Observable<ListenKey>

    @Headers(ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    @PUT("api/v1/userDataStream")
    fun keepAlive(@Query("listenKey") listenKey: String): Completable

    @Headers(ENDPOINT_SECURITY_TYPE_APIKEY_HEADER)
    @DELETE("/api/v1/userDataStream")
    fun close(@Query("listenKey") listenKey: String): Completable
}