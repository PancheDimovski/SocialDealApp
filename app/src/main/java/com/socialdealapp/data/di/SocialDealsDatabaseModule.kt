package com.socialdealapp.data.di

import android.app.Application
import androidx.room.Room
import com.socialdealapp.data.db.SocialDealDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SocialDealsDatabaseModule {

    @Provides
    @Singleton
    fun provideSocialDealDatabase(app: Application): SocialDealDatabase {
        return Room.databaseBuilder(app, SocialDealDatabase::class.java, "deals_db").build()
    }
}