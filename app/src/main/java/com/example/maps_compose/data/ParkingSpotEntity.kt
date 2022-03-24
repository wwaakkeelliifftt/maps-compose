package com.example.maps_compose.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ParkingSpotEntity(
    @PrimaryKey val id: Int? = null,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    var spotName: String = "",
    var spotColor: Float = 0.0f
)

