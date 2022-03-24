package com.example.maps_compose.presentation


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ToggleOff
import androidx.compose.material.icons.filled.ToggleOn
import androidx.compose.material.icons.filled.Traffic
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.maps_compose.domain.model.ParkingSpot
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker

@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MapScreen(
    viewModel: MapScreenViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val uiSettings = remember { MapUiSettings(zoomControlsEnabled = false) }
    var currentSpot by remember { mutableStateOf(ParkingSpot(0.0, 0.0)) }

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                backgroundColor = when {
                    viewModel.state.isCobaltMap -> Color(0x6DBA6EFC)
                    viewModel.state.isFalloutMap -> Color(0x8DFF3232)
                    viewModel.state.isRedRoads -> Color(0x6B44CDDF)
                    else -> Color(0x95FFE715)
                }
            ) {
                Icon(
                    imageVector = if (viewModel.state.isFalloutMap) {
                        Icons.Default.ToggleOff
                    } else Icons.Default.ToggleOn,
                    contentDescription = "Toggle Fallout map",
                    modifier = Modifier.combinedClickable(
                        onClick = { viewModel.onEvent(MapEvent.ToggleFalloutMap) },
                        onLongClick = { viewModel.onEvent(MapEvent.ToggleRedRoadsMap) },
                        onDoubleClick = { viewModel.onEvent(MapEvent.ToggleCobaltMap) }
                    )
                )
            }
        }
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            properties = viewModel.state.properties,
            uiSettings = uiSettings,
            onMapLongClick = { latlon ->
                viewModel.onEvent(event = MapEvent.OnMapLongClick(latLng = latlon))
            }
        ) {
            if (viewModel.state.parkingSpots.isNotEmpty()) {
                viewModel.state.parkingSpots.forEach { spot ->
                    Marker(
                        position = LatLng(spot.lat, spot.lng),
                        title = spot.spotName.ifEmpty { "Unknown.." },
                        snippet = "lat: ${spot.lat.toString().substring(0, 6)} " +
                                "lon: ${spot.lng.toString().substring(0,6)}",
                        onInfoWindowClick = {
                            currentSpot = spot
                            viewModel.onEvent(MapEvent.OpenDetailSpotWindow(spot = spot))
                            viewModel.spotDetailScreenVisible = true
                        },
                        onInfoWindowLongClick = {
                            viewModel.onEvent(MapEvent.OnInfoWindowLongClick(spot))
                        },
                        icon = BitmapDescriptorFactory.defaultMarker(spot.spotColor)
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            SpotDetainScreen(
                viewModel = viewModel,
                isVisible = viewModel.spotDetailScreenVisible,
                spot = currentSpot,
                modifier = Modifier
                    .weight(3f)
            )
            ButtonNavigation(
                viewModel = viewModel,
                modifier = Modifier
                    .weight(1f)
            )
        }

    }
}

@Composable
fun ButtonNavigation(
    viewModel: MapScreenViewModel,
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Bottom
    ) {
        Button(
            onClick = {
                viewModel.onEvent(MapEvent.DeleteAllSpots)
            },
            modifier = Modifier
                .size(44.dp)
                .offset(y = -(66.dp)),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.LightGray
            )
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "delete all spots",
                tint = if (viewModel.state.parkingSpots.isNotEmpty()) Color.Red else Color.Black,
                modifier = Modifier.scale(2.5f)
            )
        }
        Button(
            onClick = {
                viewModel.onEvent(MapEvent.Traffic)
            },
            modifier = Modifier
                .size(44.dp)
                .offset(y = -(44).dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (viewModel.state.isTrafficState) {
                    Color(0xCC26A69A)
                } else Color(0x3326A69A)
            )
        ) {
            Icon(
                imageVector = Icons.Default.Traffic,
                contentDescription = "Traffic state",
                tint = Color.Black,
                modifier = Modifier
                    .scale(2.5f)
            )
        }
    }
}