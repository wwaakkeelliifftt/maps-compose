package com.example.maps_compose.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.maps_compose.domain.repository.ParkingSpotRepository
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.MapProperties
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapScreenViewModel @Inject constructor(
    private val repository: ParkingSpotRepository
): ViewModel() {

    var state by mutableStateOf(MapState())

    fun onEvent(event: MapEvent) {
        when (event) {
            MapEvent.ToggleFalloutMap -> {
                state = state.copy(
                    properties = state.properties.copy(
                        mapStyleOptions = if (state.isFalloutMap) {
                            null
                        } else MapStyleOptions(Fallout.json),
                    ),
                    isFalloutMap = !state.isFalloutMap,
                    isCobaltMap = false,
                    isRedRoads = false
                )
            }
            MapEvent.ToggleCobaltMap -> {
                state = state.copy(
                    properties = state.properties.copy(
                        mapStyleOptions = if (state.isCobaltMap) {
                            null
                        } else MapStyleOptions(Cobalt.json)
                    ),
                    isFalloutMap = false,
                    isRedRoads = false,
                    isCobaltMap = !state.isCobaltMap
                )
            }
            MapEvent.ToggleRedRoadsMap -> {
                state = state.copy(
                    properties = state.properties.copy(
                        mapStyleOptions = if (state.isRedRoads) {
                            null
                        } else MapStyleOptions(RedRoads.json),
                        isTrafficEnabled = false
                    ),
                    isFalloutMap = false,
                    isCobaltMap = false,
                    isRedRoads = !state.isRedRoads,
                    isTrafficState = false
                )
            }
            MapEvent.Traffic -> {
                state = state.copy(
                    properties = state.properties.copy(
                        isTrafficEnabled = !state.isTrafficState
                    ),
                    isTrafficState = !state.isTrafficState
                )
            }
        }
    }
}