package com.example.maps_compose.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.maps_compose.data.SPOT_COLOR_MAP
import com.example.maps_compose.domain.model.ParkingSpot

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun SpotDetainScreen(
    viewModel: MapScreenViewModel,
    isVisible: Boolean,
    spot: ParkingSpot,
    modifier: Modifier
) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(200.dp))

        if (isVisible) {
            Column(
                modifier = Modifier
                    .background(Color.White)
            ) {
                var text by remember { mutableStateOf("") }
                OutlinedTextField(
                    label = { Text(text = "_name_") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    value = text,
                    onValueChange = {
                        text = it
                        viewModel.spotNameState = it
                    },
                    singleLine = true,
                    maxLines = 1,
                    placeholder = { Text(text = "name of spot..") },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Black,
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.LightGray,
                        unfocusedLabelColor = Color.LightGray,
                        focusedLabelColor = Color.DarkGray
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SPOT_COLOR_MAP.forEach { (key, value) ->
                        Column {
                            RadioButton(
                                selected = viewModel.spotColorState == value,
                                onClick = { viewModel.spotColorState = value },
                                colors = RadioButtonDefaults.colors(
                                    unselectedColor = when (key) {
                                        "RED" -> Color.Red
                                        "ORANGE" -> Color(0xFFFF6F00)
                                        "YELLOW" -> Color.Yellow
                                        "GREEN" -> Color.Green
                                        "CYAN" -> Color.Cyan
                                        "AZURE" -> Color(0xFF0CE3FF)
                                        "BLUE" -> Color.Blue
                                        "VIOLET" -> Color(0xFFD03FF8)
                                        "MAGENTA" -> Color.Magenta
                                        "ROSE" -> Color(0xFFEC407A)
                                        else -> Color.LightGray
                                    },
                                    selectedColor = when (key) {
                                        "RED" -> Color.Red
                                        "ORANGE" -> Color(0xFFFF6F00)
                                        "YELLOW" -> Color.Yellow
                                        "GREEN" -> Color.Green
                                        "CYAN" -> Color.Cyan
                                        "AZURE" -> Color(0xFF0CE3FF)
                                        "BLUE" -> Color.Blue
                                        "VIOLET" -> Color(0xFFD03FF8)
                                        "MAGENTA" -> Color.Magenta
                                        "ROSE" -> Color(0xFFEC407A)
                                        else -> Color.LightGray
                                    }
                                )
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                        }

                    }
                }

                Button(
                    onClick = {
                        viewModel.onEvent(MapEvent.OnUpdateSpotClick(spot = spot))
                        viewModel.spotDetailScreenVisible = false
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.End)
                ) {
                    Icon(
                        imageVector = Icons.Default.Done,
                        contentDescription = "Done"
                    )
                }

            }
        }


    }

}
