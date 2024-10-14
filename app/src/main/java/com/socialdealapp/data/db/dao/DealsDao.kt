package com.socialdealapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.socialdealapp.data.db.entity.DealsEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface DealsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeals(deals: List<DealsEntity>)

    @Query("SELECT * FROM dealsentity")
    suspend fun getAllDeals(): List<DealsEntity>

    @Query("SELECT * FROM dealsentity WHERE uniqueId=:dealUniqueId LIMIT 1")
    suspend fun getDealByUniqueId(dealUniqueId: String): DealsEntity

    @Query("SELECT * FROM dealsentity WHERE favoriteDeal = 1")
    fun getFavoriteDeals(): Flow<List<DealsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDealToFavorite(vararg deal: DealsEntity)

    @Query("DELETE FROM dealsentity")
    suspend fun deleteDeals()
}