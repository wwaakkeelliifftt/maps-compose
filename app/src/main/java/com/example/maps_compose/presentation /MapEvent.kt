package com.example.maps_compose.presentation

import com.example.maps_compose.domain.model.ParkingSpot
import com.google.android.gms.maps.model.LatLng

sealed class MapEvent() {
    object ToggleFalloutMap: MapEvent()
    object ToggleCobaltMap: MapEvent()
    object ToggleRedRoadsMap: MapEvent()
    object Traffic: MapEvent()

    data class OpenDetailSpotWindow(val spot: ParkingSpot): MapEvent()
    object DeleteAllSpots: MapEvent()
    data class OnMapLongClick(val latLng: LatLng): MapEvent()
    data class OnInfoWindowLongClick(val spot: ParkingSpot): MapEvent()
    data class OnUpdateSpotClick(val spot: ParkingSpot): MapEvent()
}
