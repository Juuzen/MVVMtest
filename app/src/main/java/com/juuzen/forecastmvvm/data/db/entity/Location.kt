package com.juuzen.forecastmvvm.data.db.entity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location(
    @Json(name = "country")
    val country: String,
    @Json(name = "lat")
    val lat: String,
    @Json(name = "localtime")
    val localtime: String,
    @Json(name = "localtime_epoch")
    val localtimeEpoch: Int,
    @Json(name = "lon")
    val lon: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "region")
    val region: String,
    @Json(name = "timezone_id")
    val timezoneId: String,
    @Json(name = "utc_offset")
    val utcOffset: String
)