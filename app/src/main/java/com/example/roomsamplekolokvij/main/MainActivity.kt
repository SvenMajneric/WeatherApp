package com.example.roomsamplekolokvij.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.example.roomsamplekolokvij.R
import com.example.roomsamplekolokvij.databinding.ActivityMainBinding
import com.example.roomsamplekolokvij.model.WeatherBaseInfo
import com.example.roomsamplekolokvij.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: AlbumViewModel by viewModels()

    private val weatherAdapter = WeatherAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvAlbums.adapter = weatherAdapter

        setupListeners()
        bind()
    }

    private fun setupListeners(){
        binding.btnSave.setOnClickListener {
            addWeatherInfo()
        }
        binding.btnDelete.setOnClickListener {
            deleteAll()
        }
        binding.btnSearch.setOnClickListener {
            fetchCurrentWeather()
        }
        weatherAdapter.listener = object : WeatherAdapter.WeatherInteractionListener{
            override fun onWeatherDeleted(weather: WeatherBaseInfo) {
                viewModel.fetchWeatherForCity(weather.name)
                deleteAlbum(weather)
            }

        }
    }

    private fun bind(){
        viewModel.getSavedWeather().observe(this, {
            setData(it)
        })

        viewModel.dailyWeatherData.observe(this, { response ->
            when(response){
                is Resource.Success -> {
                    response.data?.let {
                        binding.progressBar.visibility = View.INVISIBLE
                        binding.tvCityName.text = it.name
                        binding.tvCityMinTemp.text = it.main.temp_min.toString()
                        binding.tvCityMaxTemp.text = it.main.temp_max.toString()
                        binding.tvCountryName.text = it.sys.country
                        binding.tvWeatherState.text = it.weather[0].main
                        binding.tvCityAvgTemp.text = it.main.temp.toString()
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        binding.progressBar.visibility = View.INVISIBLE
                        binding.tvCityName.text = getString(R.string.error)
                        binding.tvCityMinTemp.text = ""
                        binding.tvCityMaxTemp.text = ""
                        binding.tvCountryName.text = ""
                        binding.tvWeatherState.text = ""
                        binding.tvCityAvgTemp.text = ""
                        Log.e("MainActivity", "An error has occurred $message")

                    }
                }
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })

    }

    private fun addWeatherInfo(){
        val name = binding.etArtist.text.toString()

        if (name.isNotBlank()){
            viewModel.saveFetchedWeather(name)
        }
    }

    private fun deleteAll(){
        viewModel.deleteAll()
    }

    private fun setData(albums: List<WeatherBaseInfo>){
        weatherAdapter.setWeather(albums)
    }

    private fun deleteAlbum(album: WeatherBaseInfo){
        viewModel.deleteAlbum(album)
    }

    private fun fetchCurrentWeather(){
        val cityName = binding.etArtist.text.toString()

        if (cityName.isNotBlank()){
            viewModel.fetchWeatherForCity(cityName)

        }

    }
}