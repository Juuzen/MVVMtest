package com.juuzen.forecastmvvm.ui.currentWeather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.juuzen.forecastmvvm.data.repository.ForecastRepository


class CurrentWeatherViewModelFactory(
       private val forecastRepository: ForecastRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrentWeatherViewModel(forecastRepository) as T
    }
}