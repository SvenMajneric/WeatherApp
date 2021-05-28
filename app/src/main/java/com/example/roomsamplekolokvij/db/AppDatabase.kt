package com.example.roomsamplekolokvij.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomsamplekolokvij.model.WeatherBaseInfo

//TODO: make an entity for search history (class City with parameter name)
@Database(entities = [WeatherBaseInfo::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun albumsDao(): AlbumsDao

}