package com.example.roomsamplekolokvij.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomsamplekolokvij.model.Weather
import com.example.roomsamplekolokvij.model.WeatherBaseInfo
import com.example.roomsamplekolokvij.repository.AlbumsRepository
import com.example.roomsamplekolokvij.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(private val albumRepo: AlbumsRepository): ViewModel() {


    val dailyWeatherData : MutableLiveData<Resource<Weather>> = MutableLiveData()

    init {
        fetchWeatherForCity("London")
    }

    fun getSavedWeather(): LiveData<List<WeatherBaseInfo>>{

        return albumRepo.getSavedWeather()
    }

    fun fetchWeatherForCity(cityName: String){
        viewModelScope.launch {
            dailyWeatherData.postValue(Resource.Loading())
            val response = albumRepo.fetchWeather(cityName)
            dailyWeatherData.postValue(handleWeatherResponse(response))
        }
    }

    fun saveFetchedWeather(cityName: String){
        viewModelScope.launch {
            dailyWeatherData.postValue(Resource.Loading())
            val response = albumRepo.fetchWeather(cityName)
            val handledResponse = handleWeatherResponse(response)
            handledResponse.data?.let {
                addWeatherBaseInfo(it.name, it.main.temp_min, it.main.temp_max)
            }
            dailyWeatherData.postValue(handledResponse)
        }
    }

    private fun handleWeatherResponse(response: Response<Weather>): Resource<Weather>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun addWeatherBaseInfo(name: String, minTemp: Double, maxTemp: Double){
        val weatherInfo = WeatherBaseInfo(0, name, minTemp, maxTemp) //TODO this is wrong or should be adapted


        viewModelScope.launch {
            albumRepo.saveCurrentWeather(weatherInfo)
        }
    }

    fun deleteAlbum(album: WeatherBaseInfo){
        viewModelScope.launch {
            albumRepo.deleteSavedWeather(album)
        }
    }

    fun deleteAll(){
        viewModelScope.launch {
            albumRepo.deleteAll()
        }
    }
}