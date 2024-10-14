package com.socialdealapp.data.dealsDescription.remote

import com.google.gson.annotations.SerializedName

data class DealsDescriptionData(
    @SerializedName("unique")
    val unique: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("company")
    val company: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("sold_label")
    val soldLabel: String,
    @SerializedName("prices")
    val prices: Prices
)

data class Prices(
    @SerializedName("price") val price: Price,
    @SerializedName("from_price") val fromPrice: Price,
    @SerializedName("price_label") val priceLabel: String? = null,
    @SerializedName("discount_label") val discountLabel: String? = null
)

data class Price(
    @SerializedName("amount") val amount: Int,
    @SerializedName("currency") val currency: Currency
)

data class Currency(
    @SerializedName("symbol") val symbol: String,
    @SerializedName("code") val code: String
)


