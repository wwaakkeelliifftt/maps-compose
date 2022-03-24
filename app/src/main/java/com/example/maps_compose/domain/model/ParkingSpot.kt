package com.example.maps_compose.domain.model

import androidx.compose.ui.graphics.Color

data class ParkingSpot(
    val lat: Double,
    val lng: Double,
    val id: Int? = null,
    var spotName: String = "",
    var spotColor: Float = 0.0f
)

