package com.juuzen.forecastmvvm.ui.currentWeather

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.juuzen.forecastmvvm.data.WeatherStackAPIService
import com.juuzen.forecastmvvm.data.network.ConnectivityInterceptorImpl
import com.juuzen.forecastmvvm.data.network.WeatherNetworkDataSource
import com.juuzen.forecastmvvm.data.network.WeatherNetworkDataSourceImpl
import com.juuzen.forecastmvvm.databinding.CurrentWeatherFragmentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch

class CurrentWeatherFragment : Fragment() {
    private var _binding : CurrentWeatherFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = CurrentWeatherFragment()
    }

    private lateinit var viewModel: CurrentWeatherViewModel

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
        viewModel = ViewModelProvider(this).get(CurrentWeatherViewModel::class.java)
        // TODO: Use the ViewModel

        val apiService = WeatherStackAPIService(ConnectivityInterceptorImpl(this.requireContext()))
        val weatherNetworkDataSource = WeatherNetworkDataSourceImpl(apiService)

        weatherNetworkDataSource.downloadedCurrentWeather.observe(viewLifecycleOwner, Observer {
            binding.currentWeatherTextView.text = it.toString()
        })


        GlobalScope.launch(Dispatchers.Main) {
            weatherNetworkDataSource.fetchCurrentWeather("Naples")
        }
    }

}