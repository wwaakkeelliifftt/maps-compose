package com.example.maps_compose

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.maps_compose.presentation.MapScreen
import com.example.maps_compose.ui.theme.MapscomposeTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MapscomposeTheme {

                val permissionState = rememberPermissionState(
                    permission = Manifest.permission.ACCESS_FINE_LOCATION
                )
                when (permissionState.status) {
                    is PermissionStatus.Granted -> MapScreen()
                    is PermissionStatus.Denied -> {
                        PermissionRequestScreen(permission = permissionState)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionRequestScreen(permission: PermissionState) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "You need to accept location permission for the work",
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(32.dp)
        )
        Spacer(modifier = Modifier.height(14.dp))
        Button(
            onClick = { permission.launchPermissionRequest() },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFB39DDB)
            )
        ) {
            Text(text = "Try again", color = Color.Black)
        }
    }
}