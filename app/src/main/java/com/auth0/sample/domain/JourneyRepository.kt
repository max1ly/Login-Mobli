package com.auth0.sample.domain

interface JourneyRepository {

    suspend fun fetchJourneyData(accessToken: String): Journey

}
