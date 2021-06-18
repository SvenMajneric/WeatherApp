package com.example.roomsamplekolokvij.repository

import androidx.lifecycle.LiveData
import com.example.roomsamplekolokvij.BuildConfig.API_KEY
import com.example.roomsamplekolokvij.db.AppDatabase
import com.example.roomsamplekolokvij.model.WeatherBaseInfo
import com.example.roomsamplekolokvij.networking.WeatherService
import javax.inject.Inject

class AlbumsRepository @Inject constructor(
    private val api: WeatherService,
    private val database: AppDatabase
) {


    private val albumsDao = database.albumsDao()

    suspend fun saveCurrentWeather(album: WeatherBaseInfo){
        albumsDao.saveCurrentWeather(album)
    }

    suspend fun deleteSavedWeather(album: WeatherBaseInfo){
        albumsDao.deleteSavedWeather(album)
    }

    suspend fun deleteAll(){
        albumsDao.deleteAll()
    }
    suspend fun fetchWeather(name: String) = api.getCityWeather(name, API_KEY)


    fun getSavedWeather(): LiveData<List<WeatherBaseInfo>>{

        return albumsDao.getSavedWeather()
    }
}