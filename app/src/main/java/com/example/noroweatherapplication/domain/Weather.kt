package com.example.noroweatherapplication.domain

/**
 * @author: Created by Ana Dimoska
 * @Date: Created on 12/13/2024
 * Represents the weather data for a specific city.
 * This class contains the details of the current weather conditions, including temperature, condition,
 * humidity, and other relevant weather information.
 *
 * @param city The name of the city for which the weather data is provided.
 * @param temperature The current temperature in Celsius.
 * @param condition A textual description of the weather condition (e.g., "Sunny", "Rainy").
 * @param conditionIcon An icon representing the current weather condition.
 * @param humidity The current humidity as a percentage.
 * @param uvIndex The current UV index.
 * @param feelsLike The temperature as it feels in Celsius (real feel).
 * @param iconUrl The URL to the icon representing the weather condition.
 */

data class Weather(
    val city: String,
    val temperature: Double,
    val condition: String,
    val conditionIcon: String,
    val humidity: Int,
    val uvIndex: Double,
    val feelsLike: Double,
    val iconUrl: String
)
