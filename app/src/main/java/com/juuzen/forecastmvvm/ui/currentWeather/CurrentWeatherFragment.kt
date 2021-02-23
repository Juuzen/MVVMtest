package com.juuzen.forecastmvvm.ui.currentWeather

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.juuzen.forecastmvvm.data.WeatherStackAPIService
import com.juuzen.forecastmvvm.data.network.ConnectivityInterceptorImpl
import com.juuzen.forecastmvvm.data.network.WeatherNetworkDataSource
import com.juuzen.forecastmvvm.data.network.WeatherNetworkDataSourceImpl
import com.juuzen.forecastmvvm.databinding.CurrentWeatherFragmentBinding
import com.juuzen.forecastmvvm.ui.Base.ScopedFragment
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.closestDI
import org.kodein.di.instance

class CurrentWeatherFragment : ScopedFragment(), DIAware {
    override val di: DI by closestDI()

    private var _binding : CurrentWeatherFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CurrentWeatherViewModel
    private val viewModelFactory: CurrentWeatherViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CurrentWeatherFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)
                .get(CurrentWeatherViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = launch {
        val currentWeather = viewModel.weather.await()
        currentWeather.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer

            binding.groupLoading.visibility = View.GONE
            updateLocation("Naples")
            updateDateToToday()
            updateTemperatures(it.temperature, it.feelslike)
            updateCondition(it.weatherDescriptions[0])
            updatePrecipitation(it.precip)
            updateWind(it.windDir, it.windSpeed)
            updateVisibility(it.visibility)

            Picasso.get()
                    .load(it.weatherIcons[0])
                    .into(binding.weatherConditionImageView)
        })
    }

    private fun chooseLocalizedUnitAbbreviation(metric: String, imperial: String) : String {
        return if (viewModel.isMetric) metric else imperial
    }


    private fun updateLocation(location: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = location
    }

    private fun updateDateToToday() {
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "Today"
    }

    private fun updateTemperatures(temperature: Double, feelsLike: Double) {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("°C", "°F")
        binding.weatherTemperatureTextView.text = "Temperature: $temperature$unitAbbreviation"
        binding.weatherTemperatureFeltTextView.text = "Feels like: $feelsLike$unitAbbreviation"
    }

    private fun updateCondition (condition: String) {
        binding.weatherConditionTextView.text = "$condition"
    }

    private fun updatePrecipitation (precip: Double) {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("mm", "in")
        binding.precipitationTextView.text = "Precipitation: $precip $unitAbbreviation"
    }

    private fun updateWind (windDirection: String, windSpeed: Double) {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("km/h", "m/h")
        binding.windTextView.text = "Wind: $windDirection, $windSpeed $unitAbbreviation"
    }

    private fun updateVisibility(visibilityDistance: Double) {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("km", "mi")
        binding.visibilityTextView.text = "Visibility: $visibilityDistance $unitAbbreviation"
    }
}