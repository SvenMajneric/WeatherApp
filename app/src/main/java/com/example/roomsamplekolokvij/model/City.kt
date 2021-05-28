package com.example.roomsamplekolokvij.model

import com.squareup.moshi.Json
import java.io.Serializable

data class City(
    @field: Json(name = "id")
    val id: Int = 0,
    @field: Json(name = "name")
    val name: String = "",
    @field: Json(name = "country")
    val country: String = ""
): Serializable
