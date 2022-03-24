package com.example.maps_compose.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.maps_compose.domain.model.ParkingSpot

fun ParkingSpotEntity.toParkingSpot(): ParkingSpot {
    return ParkingSpot(
        lat = this.latitude,
        lng = this.longitude,
        id = this.id,
        spotName = this.spotName,
        spotColor = this.spotColor
    )
}

fun ParkingSpot.toParkingSpotEntity(): ParkingSpotEntity {
    return ParkingSpotEntity(
        latitude = this.lat,
        longitude = this.lng,
        id = this.id,
        spotName = this.spotName,
        spotColor = this.spotColor
    )
}

