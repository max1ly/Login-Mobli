package com.mvi.example.stop

data class Request(
    val destination: DestinationXX,
    val ignoreAvailabilityRules: Boolean,
    val journeyPlanGroupId: String,
    val origin: OriginXX,
    val passengers: Passengers,
    val planMode: String,
    val providerId: String
)