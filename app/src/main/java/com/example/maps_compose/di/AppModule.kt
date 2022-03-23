package com.example.maps_compose.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.maps_compose.data.ParkingSpotDao
import com.example.maps_compose.data.ParkingSpotDatabase
import com.example.maps_compose.data.ParkingSpotRepositoryImpl
import com.example.maps_compose.domain.repository.ParkingSpotRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideParkingSpotDatabase(app: Application): ParkingSpotDatabase {
        return  Room.databaseBuilder(
            app,
            ParkingSpotDatabase::class.java,
            "spot_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideParkingSpotRepository(database: ParkingSpotDatabase): ParkingSpotRepository {
        return ParkingSpotRepositoryImpl(database.dao)
    }

}
