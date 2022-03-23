package com.example.maps_compose.domain.repository

import com.example.maps_compose.domain.model.ParkingSpot
import kotlinx.coroutines.flow.Flow

interface ParkingSpotRepository {

    suspend fun insertParkingSpot(spot: ParkingSpot)

    suspend fun updateParkingSpot(spot: ParkingSpot)

    suspend fun deleteParkingSpot(spot: ParkingSpot)

    fun getAllParkingSpots(): Flow<List<ParkingSpot>>

}