package com.example.noroweatherapplication.domain

/**
 * @author: Created by Ana Dimoska
 * @Date: Created on 12/13/2024
 * Interface with method getWeather() for a city
 */

interface WeatherRepository {
    suspend fun getWeather(city: String): Weather
}
