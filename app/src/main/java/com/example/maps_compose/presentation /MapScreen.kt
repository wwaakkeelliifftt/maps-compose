package com.example.maps_compose.presentation


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.ToggleOff
import androidx.compose.material.icons.filled.ToggleOn
import androidx.compose.material.icons.filled.Traffic
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MapScreen(
    viewModel: MapScreenViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val uiSettings = remember { MapUiSettings(zoomControlsEnabled = false) }
    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { }
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
            onMapLongClick = {
                // todo
            }
        ) {
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
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
}