package com.juuzen.forecastmvvm.ui.futureWeather.futureWeatherList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.juuzen.forecastmvvm.R

class FutureWeatherListFragment : Fragment() {

    companion object {
        fun newInstance() = FutureWeatherListFragment()
    }

    private lateinit var viewModel: FutureWeatherListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.future_weather_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FutureWeatherListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}