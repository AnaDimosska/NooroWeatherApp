package com.example.noroweatherapplication.data.model

/**
 * @author: Created by Ana Dimoska
 * @Date: Created on 12/13/2024
 * Represents the response from the weather API containing weather data for a specific location.
 *
 * @param location The location details of the city (name, region, country, etc.).
 * @param current The current weather information (temperature, condition, humidity, etc.).
 */

data class WeatherResponse(
    val location: Location,
    val current: Current
)

data class Location(
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    val tz_id: String,
    val localtime: String
)

data class Current(
    val temp_c: Double,
    val condition: Condition,
    val humidity: Int,
    val uv: Double,
    val feelslike_c: Double
)

data class Condition(
    val text: String,
    val icon: String,
    val code: Int
)
