package com.example.roomsamplekolokvij.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomsamplekolokvij.model.WeatherBaseInfo

@Dao
interface AlbumsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCurrentWeather(weatherBaseInfo: WeatherBaseInfo)


    @Query("SELECT * FROM WeatherBaseInfo")
    fun getSavedWeather(): LiveData<List<WeatherBaseInfo>>

    @Delete
    suspend fun deleteSavedWeather(album: WeatherBaseInfo)

    @Query("DELETE FROM WeatherBaseInfo")
    suspend fun deleteAll()
}