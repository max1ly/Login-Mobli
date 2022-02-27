package com.mvi.example.stop

data class DestinationX(
    val arrivalTimeEstimated: String,
    val arrivalTimePlanned: String,
    val latLng: LatLngX,
    val name: String,
    val stopCode: String,
    val stopId: String,
    val stopSequence: Int,
    val type: String
)