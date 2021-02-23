package com.juuzen.forecastmvvm

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.juuzen.forecastmvvm.data.WeatherStackAPIService
import com.juuzen.forecastmvvm.data.db.ForecastDatabase
import com.juuzen.forecastmvvm.data.network.ConnectivityInterceptor
import com.juuzen.forecastmvvm.data.network.ConnectivityInterceptorImpl
import com.juuzen.forecastmvvm.data.network.WeatherNetworkDataSource
import com.juuzen.forecastmvvm.data.network.WeatherNetworkDataSourceImpl
import com.juuzen.forecastmvvm.data.provider.UnitProvider
import com.juuzen.forecastmvvm.data.provider.UnitProviderImpl
import com.juuzen.forecastmvvm.data.repository.ForecastRepository
import com.juuzen.forecastmvvm.data.repository.ForecastRepositoryImpl
import com.juuzen.forecastmvvm.ui.currentWeather.CurrentWeatherViewModelFactory
import org.kodein.di.*
import org.kodein.di.android.x.androidXModule

class ForecastApplication() : Application(), DIAware {
    override val di: DI = DI.lazy {
        import(androidXModule(this@ForecastApplication))

        bind() from singleton { ForecastDatabase.invoke(instance()) }
        bind() from singleton { instance<ForecastDatabase>().currentWeatherDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { WeatherStackAPIService(instance()) }
        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind <ForecastRepository>() with singleton { ForecastRepositoryImpl(instance(), instance()) }
        bind<UnitProvider>() with singleton { UnitProviderImpl(instance()) }
        bind() from provider { CurrentWeatherViewModelFactory(instance(), instance()) }

    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}