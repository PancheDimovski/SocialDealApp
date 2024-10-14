package com.socialdealapp.domain.dealDescriptionDetails.model

data class DealItemDescriptionData(
    // deals data
    val uniqueId: String,
    val title: String,
    val imageUrl: String,
    val soldLabel: String,
    val companyName: String,
    val city: String,
    val originalPrice: Int?,
    val fromPrice: Int?,
    val symbol: String?,
    val currencyCode: String?,
    val discountLabel: String?,

    // description data
    val descUniqueId: String,
    val descTitle: String?,
    val descCompany: String,
    val description: String,
    val descCity: String,
    val descSoldLabel: String,
    val descPrice: Int?,
    val descCurrencySymbol: String?,
    val descCurrencyCode: String?,
    val descFromPrice: Int? = null,
    val descPriceLabel: String? = null,
    val descDiscountLabel: String? = null
)