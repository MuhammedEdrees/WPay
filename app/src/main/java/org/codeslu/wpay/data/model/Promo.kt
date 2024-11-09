package org.codeslu.wpay.data.model

data class Promo(
    val title: String = "",
    val description: String = "",
    val hasDiscount: Boolean = false,
    val discountPercentage: Int = 0,
)
