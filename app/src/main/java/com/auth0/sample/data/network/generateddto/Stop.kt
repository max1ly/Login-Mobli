package com.auth0.sample.data.network.generateddto

import com.mvi.example.stop.LatLngXXX

data class Stop(
    val arrivalTimePlanned: String,
    val departureTimePlanned: String,
    val id: String,
    val latLng: LatLngXXX,
    val name: String,
    val stopCode: String,
    val stopIndex: Int,
    val stopSequence: Int,
    val zoneId: String
)
