package com.example.roomsamplekolokvij.model

import com.squareup.moshi.Json

data class Coord(
    @field: Json(name = "lon")
    val lon: Double = 0.0,
    @field: Json(name = "lat")
    val lat: Double = 0.0
)