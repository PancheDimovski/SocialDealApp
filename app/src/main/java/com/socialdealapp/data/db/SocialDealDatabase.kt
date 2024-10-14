package com.socialdealapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.socialdealapp.data.db.dao.DealsDao
import com.socialdealapp.data.db.entity.DealsEntity

@Database(entities = [DealsEntity::class], version = 1, exportSchema = false)
abstract class SocialDealDatabase : RoomDatabase() {

    abstract val dealsDao: DealsDao
}