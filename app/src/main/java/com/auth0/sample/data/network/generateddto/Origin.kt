package com.mvi.example.stop

data class Origin(
    val departureTimeEstimated: String,
    val departureTimePlanned: String,
    val latLng: LatLngXX,
    val name: String,
    val stopCode: String,
    val stopId: String,
    val stopSequence: Int,
    val type: String
)