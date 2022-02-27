package com.auth0.sample.data.network

import com.auth0.sample.domain.Journey
import com.auth0.sample.domain.JourneyRepository
import com.auth0.sample.domain.Stop
import javax.inject.Inject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JourneyRepositoryImpl @Inject constructor(): JourneyRepository {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.apps.dev.operator.mobli.io/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val remoteService = retrofit.create(JourneyRemoteService::class.java)

    override suspend fun fetchJourneyData(accessToken: String): Journey {
        val dto = remoteService.fetchJourney("Bearer $accessToken")
        val stops = dto.journey.legs.flatMap { it.stops }.map {
            Stop(it.id, it.name, it.stopCode, it.arrivalTimePlanned.replace("T", " ").removeSuffix("Z"))
        }
        return Journey(stops)
    }
}
