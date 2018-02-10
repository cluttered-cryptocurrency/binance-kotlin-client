package com.cluttered.cryptocurrency.services

import com.cluttered.cryptocurrency.model.ServerTime
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.GET

interface PublicService {

    companion object {
        const val API_V1: String = "api/v1"
    }

    @GET("$API_V1/ping")
    fun ping(): Completable

    @GET("$API_V1/time")
    fun time(): Observable<ServerTime>
}