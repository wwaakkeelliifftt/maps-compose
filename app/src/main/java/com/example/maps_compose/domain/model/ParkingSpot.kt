package com.example.maps_compose.domain.model

import androidx.compose.ui.graphics.Color
import com.example.maps_compose.data.ParkingSpotEntity

data class ParkingSpot(
    val lat: Double,
    val lng: Double,
    val id: Int? = null,
    var spotName: String,
    var spotColor: Color
)
