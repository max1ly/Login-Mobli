package com.mvi.example.stop

data class Fares(
    val additionalFees: List<Any>,
    val bookableFares: Int,
    val commissions: List<Any>,
    val currency: String,
    val discounts: List<Any>,
    val faresExtraItems: List<Any>,
    val faresItems: List<FaresItem>,
    val promotions: List<Any>,
    val tax: Double,
    val totalBaseFares: Int,
    val totalBaseFaresDiscount: Int,
    val totalCommission: Int,
    val totalDiscount: Int,
    val totalFares: Int
)