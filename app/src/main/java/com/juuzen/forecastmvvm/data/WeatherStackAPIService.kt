package com.juuzen.forecastmvvm.data

import com.juuzen.forecastmvvm.data.network.ConnectivityInterceptor
import com.juuzen.forecastmvvm.data.network.ConnectivityInterceptorImpl
import com.juuzen.forecastmvvm.data.network.response.CurrentWeatherResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val KEY : String = "51d7bb2ce99e785dc66cb3afa9522e63"
const val BASEURL : String = "http://api.weatherstack.com/"

interface WeatherStackAPIService {

    @GET("current")
    suspend fun getCurrentWeather(
            @Query("query") location : String,
        ) : CurrentWeatherResponse

    companion object {
        operator fun invoke(
                connectivityInterceptor: ConnectivityInterceptor
        ) : WeatherStackAPIService {
            val requestInterceptor = Interceptor { chain ->

                val url = chain.request()
                        .url()
                        .newBuilder()
                        .addQueryParameter("access_key", KEY)
                        .build()

                val request = chain.request()
                        .newBuilder()
                        .url(url)
                        .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(requestInterceptor)
                    .addInterceptor(connectivityInterceptor)
                    .build()

            return Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASEURL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
                    .create(WeatherStackAPIService::class.java)
        }
    }
}