package com.example.noroweatherapplication.data.repo

import com.example.noroweatherapplication.data.api.WeatherApiService
import com.example.noroweatherapplication.domain.Weather
import com.example.noroweatherapplication.domain.WeatherRepository

import javax.inject.Inject

/**
 * @author: Created by Ana Dimoska
 * @Date: Created on 12/13/2024
 * Implementation of the [WeatherRepository] interface that interacts with the weather API service
 * to fetch weather data for a specific city and maps the response to a [Weather] domain model.
 *
 * @param apiService The API service for fetching weather data.
 * @param apiKey The API key used for authenticating the request.
 */
class WeatherRepositoryImpl @Inject constructor(
    private val apiService: WeatherApiService,
    private val apiKey: String
) : WeatherRepository {
    override suspend fun getWeather(city: String): Weather {
        val response = apiService.getWeather(apiKey, city)
        return Weather(
            city = response.location.name,
            temperature = response.current.temp_c,
            condition = response.current.condition.text,
            conditionIcon = response.current.condition.icon,
            humidity = response.current.humidity,
            uvIndex = response.current.uv,
            feelsLike = response.current.feelslike_c,
            iconUrl = "https:${response.current.condition.icon}"
        )
    }
}
