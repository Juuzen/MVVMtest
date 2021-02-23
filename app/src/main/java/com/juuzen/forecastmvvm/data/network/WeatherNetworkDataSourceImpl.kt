package com.juuzen.forecastmvvm.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.juuzen.forecastmvvm.data.WeatherStackAPIService
import com.juuzen.forecastmvvm.data.network.response.CurrentWeatherResponse
import com.juuzen.forecastmvvm.utils.NoConnectivityException

private const val TAG = "WeatherNetworkDataSourc"

class WeatherNetworkDataSourceImpl(
        private val weatherStackAPIService : WeatherStackAPIService
) : WeatherNetworkDataSource {

    private val _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()

    override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadedCurrentWeather

    override suspend fun fetchCurrentWeather(location: String) {
        try {
            val fetchedCurrentWeather = weatherStackAPIService
                    .getCurrentWeather(location)

            _downloadedCurrentWeather.postValue(fetchedCurrentWeather)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }
}