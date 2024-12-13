package com.example.noroweatherapplication.domain


import javax.inject.Inject

/**
 * @author: Created by Ana Dimoska
 * @Date: Created on 12/13/2024
 * Use case responsible for retrieving weather information for a specific city.
 * It interacts with the [WeatherRepository] to fetch the weather data and return it in the domain model format.
 *
 * @param repository The [WeatherRepository] used to fetch weather data.
 */

class GetWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(city: String): Weather {
        return repository.getWeather(city)
    }
}
