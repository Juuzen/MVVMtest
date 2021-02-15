package com.juuzen.forecastmvvm.data.network.response

import com.juuzen.forecastmvvm.data.db.entity.CurrentWeatherEntry
import com.juuzen.forecastmvvm.data.db.entity.Location
import com.juuzen.forecastmvvm.data.db.entity.Request
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentWeatherResponse(
        @Json(name = "current")
    val currentWeatherEntry: CurrentWeatherEntry,
        @Json(name = "location")
    val location: Location,
        @Json(name = "request")
    val request: Request
)