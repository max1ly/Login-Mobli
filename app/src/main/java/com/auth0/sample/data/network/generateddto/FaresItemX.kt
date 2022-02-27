package com.mvi.example.stop

data class FaresItemX(
    val fares: Int,
    val faresPerPax: Int,
    val number: Int,
    val otherFees: List<Any>,
    val paxCountDiscount: Int,
    val persona: String,
    val type: String
)