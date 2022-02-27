package com.mvi.example.stop

data class FaresItem(
    val fares: Int,
    val number: Int,
    val otherFees: List<Any>,
    val type: String
)