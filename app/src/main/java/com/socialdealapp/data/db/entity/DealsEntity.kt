package com.socialdealapp.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "dealsentity")
data class DealsEntity(
    @PrimaryKey val uniqueId: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
    @ColumnInfo(name = "soldLabel") val soldLabel: String,
    @ColumnInfo(name = "companyName") val companyName: String,
    @ColumnInfo(name = "city") val city: String,
    @ColumnInfo(name = "originalPrice") val originalPrice: Int?,
    @ColumnInfo(name = "fromPrice") val fromPrice: Int?,
    @ColumnInfo(name = "symbol") val symbol: String?,
    @ColumnInfo(name = "currencyCode") val currencyCode: String?,
    @ColumnInfo(name = "discountLabel") val discountLabel: String?,
    @ColumnInfo(name = "favoriteDeal") val favoriteDeal: Boolean?
)