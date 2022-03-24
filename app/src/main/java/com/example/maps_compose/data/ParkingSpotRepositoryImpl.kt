package com.example.maps_compose.data

import com.example.maps_compose.domain.model.ParkingSpot
import com.example.maps_compose.domain.repository.ParkingSpotRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ParkingSpotRepositoryImpl @Inject constructor(
    private val dao: ParkingSpotDao
): ParkingSpotRepository {

    override suspend fun insertParkingSpot(spot: ParkingSpot) {
        dao.insertParkingSpot(spot.toParkingSpotEntity())
    }

    override suspend fun updateParkingSpot(spot: ParkingSpot) {
        dao.updateParkingSpot(spot.toParkingSpotEntity())
    }

    override suspend fun deleteParkingSpot(spot: ParkingSpot) {
        dao.deleteParkingSpot(spot.toParkingSpotEntity())
    }

    override fun getAllParkingSpots(): Flow<List<ParkingSpot>> {
        return dao.getAllParkingSpots().map { spots ->
            spots.map { it.toParkingSpot() }
        }
    }

    override suspend fun deleteAllParkingSpots() {
        dao.deleteAllParkingSpots()
    }
}