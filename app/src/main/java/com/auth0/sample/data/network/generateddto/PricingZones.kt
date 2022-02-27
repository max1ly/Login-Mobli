package com.mvi.example.stop

data class PricingZones(
    val allZoneIds: List<String>,
    val allZones: List<String>,
    val destination: String,
    val destinationZoneId: String,
    val origin: String,
    val originZoneId: String
)