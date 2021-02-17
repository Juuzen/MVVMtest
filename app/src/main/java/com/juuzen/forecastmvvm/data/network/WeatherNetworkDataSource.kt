package com.juuzen.forecastmvvm.data.network

import androidx.lifecycle.LiveData
import com.juuzen.forecastmvvm.data.network.response.CurrentWeatherResponse

interface WeatherNetworkDataSource {
    val downloadedCurrentWeather : LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(location: String)
}