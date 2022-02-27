package com.auth0.sample.data.network

data class StopDto(
    val arrivalTimePlanned: String,
    val departureTimePlanned: String,
    val id: String,
    val latLng: LatLng,
    val name: String,
    val stopCode: String,
    val stopIndex: Int,
    val stopSequence: Int,
    val zoneId: String
)

data class LatLng(
    val lat: Double,
    val lng: Double
)
