package com.example.noroweatherapplication.data.api

import com.example.noroweatherapplication.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query
/**
 * @author: Created by Ana Dimoska
 * @Date: Created on 12/13/2024
 * Interface to interact with the weather API for fetching current weather data.
 *
 * @param apiKey The API key for authentication.
 * @param city The city for which weather data is requested.
 *
 * @return A [WeatherResponse] containing the current weather details.
 */

interface WeatherApiService {
    @GET("current.json")
    suspend fun getWeather(
        @Query("key") apiKey: String,
        @Query("q") city: String
    ): WeatherResponse
}
