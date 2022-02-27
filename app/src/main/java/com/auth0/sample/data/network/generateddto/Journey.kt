package com.mvi.example.stop

import com.mvi.example.generateddto.Leg

data class Journey(
    val agent: Agent,
    val arrivalTimeEstimated: String,
    val arrivalTimePlanned: String,
    val co2Emission: Int,
    val departureTimeEstimated: String,
    val departureTimePlanned: String,
    val destination: Destination,
    val distance: Int,
    val fares: Fares,
    val id: String,
    val journeyIcon: String,
    val lastModified: String,
    val legs: List<Leg>,
    val orgId: String,
    val organisation: OrganisationX,
    val origin: OriginX,
    val ownerId: String,
    val payments: List<Any>,
    val quotedFaresInclLinked: Int,
    val request: Request,
    val status: String
)