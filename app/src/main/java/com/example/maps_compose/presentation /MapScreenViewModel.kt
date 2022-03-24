package com.example.maps_compose.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maps_compose.data.SPOT_COLOR_MAP
import com.example.maps_compose.domain.model.ParkingSpot
import com.example.maps_compose.domain.repository.ParkingSpotRepository
import com.google.android.gms.maps.model.MapStyleOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapScreenViewModel @Inject constructor(
    private val repository: ParkingSpotRepository
): ViewModel() {

    var state by mutableStateOf(MapState())

    var spotDetailScreenVisible by mutableStateOf(false)
    var spotNameState by mutableStateOf("")
    var spotColorState by mutableStateOf(0.0f)

    init {
        viewModelScope.launch {
            repository.getAllParkingSpots().collectLatest { spots ->
                state = state.copy(
                    parkingSpots = spots
                )
            }
        }
    }

    fun onEvent(event: MapEvent) {
        when (event) {
            is MapEvent.ToggleFalloutMap -> {
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
            is MapEvent.ToggleCobaltMap -> {
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
            is MapEvent.ToggleRedRoadsMap -> {
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
            is MapEvent.Traffic -> {
                state = state.copy(
                    properties = state.properties.copy(
                        isTrafficEnabled = !state.isTrafficState
                    ),
                    isTrafficState = !state.isTrafficState
                )
            }

            is MapEvent.OnMapLongClick -> {
                viewModelScope.launch {
                    repository.insertParkingSpot(
                        ParkingSpot(
                            lat = event.latLng.latitude,
                            lng = event.latLng.longitude
                        )
                    )
                }
            }
            is MapEvent.OnInfoWindowLongClick -> {
                viewModelScope.launch {
                    repository.deleteParkingSpot(event.spot)
                }
            }
            is MapEvent.OnUpdateSpotClick -> {
                viewModelScope.launch {
                    repository.updateParkingSpot(
                        event.spot.copy(
                            spotColor = spotColorState,
                            spotName = spotNameState
                        )
                    )
                }
            }
            is MapEvent.DeleteAllSpots -> {
                viewModelScope.launch {
                    repository.deleteAllParkingSpots()
                }
            }

        }
    }
}