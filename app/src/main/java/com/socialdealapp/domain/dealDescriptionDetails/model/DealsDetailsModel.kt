package com.socialdealapp.domain.dealDescriptionDetails.model

data class DealsDetailsModel (
    val uniqueId: String,
    val title: String,
    val description: String,
    val soldLabel: String,
    val company: String,
    val city: String,
    val originalPrice: Int? = null,
    val fromPrice: Int?,
    val currencySymbol: String?,
    val currencyCode: String?,
    val priceLabel: String? = null,
    val discountLabel: String?
)