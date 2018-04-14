package com.cluttered.cryptocurrency.services

import com.cluttered.cryptocurrency.model.general.ExchangeInfo
import com.cluttered.cryptocurrency.model.general.ServerTime
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.GET

interface GeneralService {

    @GET("api/v1/ping")
    fun ping(): Completable

    @GET("api/v1/time")
    fun time(): Observable<ServerTime>

    @GET("api/v1/exchangeInfo")
    fun exchangeInfo(): Observable<ExchangeInfo>
}