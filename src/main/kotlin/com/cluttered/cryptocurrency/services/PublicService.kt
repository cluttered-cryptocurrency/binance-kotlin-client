package com.cluttered.cryptocurrency.services

import io.reactivex.Completable
import retrofit2.http.GET

interface PublicService {

    companion object {
        const val API_V1: String = "api/v1"
    }

    @GET("$API_V1/ping")
    fun pingCompletable(): Completable
}