package com.mvi.example.generateddto

import com.mvi.example.stop.DestinationX
import com.mvi.example.stop.FaresX
import com.mvi.example.stop.Geometry
import com.mvi.example.stop.Organisation
import com.mvi.example.stop.Origin
import com.auth0.sample.data.network.generateddto.Stop

data class Leg(
    val agencyId: String,
    val agencyName: String,
    val attributes: List<Any>,
    val co2Emission: Int,
    val destination: DestinationX,
    val distance: Int,
    val duration: Int,
    val fares: FaresX,
    val geometry: Geometry,
    val headsign: String,
    val id: String,
    val mode: String,
    val modeIcon: String,
    val orgId: String,
    val organisation: Organisation,
    val origin: Origin,
    val productCode: String,
    val productName: String,
    val route: String,
    val routeId: String,
    val routeLongName: String,
    val routeShortName: String,
    val serviceType: String,
    val stops: List<Stop>,
    val tripId: String
)
