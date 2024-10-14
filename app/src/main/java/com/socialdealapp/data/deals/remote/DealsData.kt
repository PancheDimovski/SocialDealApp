package com.socialdealapp.data.deals.remote

import com.google.gson.annotations.SerializedName

data class DealsData (
    @SerializedName("num_deals") val numDeals: Int,
    @SerializedName("deals") val deals: List<Deal>
)

data class Deal(
    @SerializedName("unique") val unique: String,
    @SerializedName("title") val title: String,
    @SerializedName("image") val image: String,
    @SerializedName("sold_label") val soldLabel: String,
    @SerializedName("company") val company: String,
    @SerializedName("city") val city: String,
    @SerializedName("prices") val prices: Prices
)

data class Prices(
    @SerializedName("price") val price: Price?,
    @SerializedName("from_price") val fromPrice: Price?,
    @SerializedName("price_label") val priceLabel: String?,
    @SerializedName("discount_label") val discountLabel: String?
)

data class Price(
    @SerializedName("amount") val amount: Int,
    @SerializedName("currency") val currency: Currency
)

data class Currency(
    @SerializedName("symbol") val symbol: String,
    @SerializedName("code") val code: String
)