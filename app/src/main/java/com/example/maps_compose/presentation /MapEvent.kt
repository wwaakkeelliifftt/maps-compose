package com.example.maps_compose.presentation

sealed class MapEvent() {
    object ToggleFalloutMap: MapEvent()
    object ToggleCobaltMap: MapEvent()
    object ToggleRedRoadsMap: MapEvent()
    object Traffic: MapEvent()
}