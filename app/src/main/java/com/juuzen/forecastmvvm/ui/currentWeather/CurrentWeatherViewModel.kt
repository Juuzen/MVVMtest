package com.juuzen.forecastmvvm.ui.currentWeather

import androidx.lifecycle.ViewModel
import com.juuzen.forecastmvvm.data.provider.UnitProvider
import com.juuzen.forecastmvvm.data.repository.ForecastRepository
import com.juuzen.forecastmvvm.utils.UnitSystem
import com.juuzen.forecastmvvm.utils.lazyDeferred

class CurrentWeatherViewModel(
        private val forecastRepository: ForecastRepository,
        unitProvider: UnitProvider
) : ViewModel() {
    private val unitCode = unitProvider.getUnitCode()
    private val unitSystem = unitProvider.getUnitSystem()
    val isMetric : Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(unitCode)
    }
}