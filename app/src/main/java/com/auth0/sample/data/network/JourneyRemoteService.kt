package com.auth0.sample.data.network

import com.mvi.example.stop.JourneyDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface JourneyRemoteService {

    @Headers(
        "Host: api.apps.dev.operator.mobli.io",
        "content-type: application/json",
    )
    @GET("driver-app/transit-route/poc")
    suspend fun fetchJourney(@Header("Authorization") authorization: String): JourneyDto
}
