package com.juuzen.forecastmvvm.ui.currentWeather

import androidx.lifecycle.ViewModel
import com.juuzen.forecastmvvm.data.repository.ForecastRepository
import com.juuzen.forecastmvvm.utils.lazyDeferred

class CurrentWeatherViewModel(
        private val forecastRepository: ForecastRepository
) : ViewModel() {
    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather()
    }
}