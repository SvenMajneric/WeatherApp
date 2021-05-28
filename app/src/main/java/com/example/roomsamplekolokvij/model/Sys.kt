package com.example.roomsamplekolokvij.model

import com.squareup.moshi.Json

data class Sys(
    @field: Json(name = "type")
    val type: Int = 0,
    @field: Json(name = "id")
    val id: Int = 0,
    @field: Json(name = "message")
    val message: Double = 0.0,
    @field: Json(name = "country")
    val country: String = "",
    @field: Json(name = "sunrise")
    val sunrise: Int = 0,
    @field: Json(name = "sunset")
    val sunset: Int = 0
)