package com.example.maps_compose.data

import com.example.maps_compose.domain.model.ParkingSpot

fun ParkingSpotEntity.toParkingSpot(): ParkingSpot {
    return ParkingSpot(
        lat = this.lat,
        lng = this.lng,
        id = this.id,
        spotName = this.spotName,
        spotColor = this.spotColor
    )
}

fun ParkingSpot.toParkingSpotEntity(): ParkingSpotEntity {
    return ParkingSpotEntity(
        lat = this.lat,
        lng = this.lng,
        id = this.id,
        spotName = this.spotName,
        spotColor = this.spotColor
    )
}

