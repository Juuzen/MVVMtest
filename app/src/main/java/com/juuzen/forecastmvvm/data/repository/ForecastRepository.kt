package com.juuzen.forecastmvvm.data.repository

import androidx.lifecycle.LiveData
import com.juuzen.forecastmvvm.data.db.entity.CurrentWeatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather(unitCode: String) : LiveData<CurrentWeatherEntry>
}