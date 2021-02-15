package com.juuzen.forecastmvvm.data.db.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

const val CURRENT_WEATHER_ID = 0

@Entity(tableName = "current_weather")
@JsonClass(generateAdapter = true)
data class CurrentWeatherEntry(
    @Json(name = "feelslike")
    val feelslike: Double,
    @Json(name = "is_day")
    val isDay: String,
    @Json(name = "observation_time")
    val observationTime: String,
    @Json(name = "precip")
    val precip: Double,
    @Json(name = "pressure")
    val pressure: Double,
    @Json(name = "temperature")
    val temperature: Double,
    @Json(name = "uv_index")
    val uvIndex: Double,
    @Json(name = "visibility")
    val visibility: Double,
    @Json(name = "weather_code")
    val weatherCode: Int,
    @Json(name = "weather_descriptions")
    val weatherDescriptions: List<String>,
    @Json(name = "weather_icons")
    val weatherIcons: List<String>,
    @Json(name = "wind_degree")
    val windDegree: Double,
    @Json(name = "wind_dir")
    val windDir: String,
    @Json(name = "wind_speed")
    val windSpeed: Double
) {
    @PrimaryKey(autoGenerate = false)
    var id = CURRENT_WEATHER_ID
}

class StringTypeConverter {
    @TypeConverter
    fun fromListToString(list : List<String>) : String = list.joinToString(separator = "`")

    @TypeConverter
    fun fromStringToList(string : String) : List<String> = string.split("`").map { it }
}