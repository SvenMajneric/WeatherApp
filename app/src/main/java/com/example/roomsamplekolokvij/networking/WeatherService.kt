package com.example.roomsamplekolokvij.networking

import com.example.roomsamplekolokvij.model.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather")
    suspend fun getCityWeather(@Query("q") city: String,
                           @Query("appid") key: String): Response<Weather>

}