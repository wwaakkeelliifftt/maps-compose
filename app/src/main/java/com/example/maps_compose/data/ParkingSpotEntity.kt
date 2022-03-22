package com.example.maps_compose.data

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ParkingSpotEntity(
    val lat: Double,
    val lng: Double,
    var spotName: String,
    var spotColor: Color,
    @PrimaryKey val id: Int? = null
) {
    fun changeName(newName: String): ParkingSpotEntity {
        return this.copy(spotName = newName)
    }

    fun changeColor(newColor: Color): ParkingSpotEntity {
        return this.copy(spotColor = newColor)
    }
}