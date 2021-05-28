package com.example.roomsamplekolokvij.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherBaseInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val tempMin: Double,
    @ColumnInfo
    val tempMax: Double
)
