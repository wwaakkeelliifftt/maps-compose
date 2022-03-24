package com.example.maps_compose.presentation

import com.example.maps_compose.domain.model.ParkingSpot
import com.google.maps.android.compose.MapProperties

data class MapState(
    val properties: MapProperties = MapProperties(
        isMyLocationEnabled = true
    ),
    val parkingSpots: List<ParkingSpot> = emptyList(),
    val isFalloutMap: Boolean = false,
    val isCobaltMap: Boolean = false,
    val isRedRoads: Boolean = false,
    val isTrafficState: Boolean = false
)

