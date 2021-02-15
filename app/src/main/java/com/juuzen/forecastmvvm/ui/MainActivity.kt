package com.juuzen.forecastmvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.juuzen.forecastmvvm.R
import com.juuzen.forecastmvvm.databinding.ActivityMainBinding
import com.juuzen.forecastmvvm.ui.currentWeather.CurrentWeatherFragment
import com.juuzen.forecastmvvm.ui.futureWeather.futureWeatherList.FutureWeatherListFragment
import com.juuzen.forecastmvvm.ui.settings.SettingsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val currentWeatherFragment = CurrentWeatherFragment()
        val futureWeatherListFragment = FutureWeatherListFragment()
        val settingsFragment = SettingsFragment()

        makeCurrentFragment(currentWeatherFragment)

        binding.bottomNavBar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.currentWeatherFragment -> makeCurrentFragment(currentWeatherFragment)
                R.id.futureWeatherListFragment -> makeCurrentFragment(futureWeatherListFragment)
                R.id.settingsFragment -> makeCurrentFragment(settingsFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
    }
}