package com.socialdealapp.domain.deals.model

data class DealsModel(
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
    val isAddedToFavorite: Boolean,
)