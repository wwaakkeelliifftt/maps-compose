package com.example.maps_compose.data

import androidx.compose.ui.graphics.Color
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ParkingSpotDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertParkingSpot(spot: ParkingSpotEntity)

    @Update
    suspend fun updateParkingSpot(spot: ParkingSpotEntity)

    @Delete
    suspend fun deleteParkingSpot(spot: ParkingSpotEntity)

    @Query("SELECT * FROM parkingspotentity")
    fun getAllParkingSpots(): Flow<List<ParkingSpotEntity>>

}