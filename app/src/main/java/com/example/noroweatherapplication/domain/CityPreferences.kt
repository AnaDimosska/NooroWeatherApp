package com.example.noroweatherapplication.domain

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * @author: Created by Ana Dimoska
 * @Date: Created on 12/13/2024
 * Data class representing weather data for a city.
 *
 * @property name The name of the city.
 * @property temperature The current temperature in the city.
 * @property iconUrl The URL of the weather icon for the city.
 */

data class CityWeather(
    val name: String,
    val temperature: String,
    val iconUrl: String
)

val Context.dataStore by preferencesDataStore("city_preferences")

class CityPreferences(private val context: Context) {

    private val CITIES_KEY = stringPreferencesKey("saved_cities")
    private val gson = Gson()

    // Get the saved list of cities
    val savedCities: Flow<List<CityWeather>> = context.dataStore.data.map { preferences ->
        val citiesJson = preferences[CITIES_KEY]
        if (!citiesJson.isNullOrEmpty()) {
            gson.fromJson(citiesJson, object : TypeToken<List<CityWeather>>() {}.type)
        } else {
            emptyList()
        }
    }

    // Save a city with its weather data
    suspend fun saveCity(cityWeather: CityWeather) {
        context.dataStore.edit { preferences ->
            val currentCities = preferences[CITIES_KEY]?.let {
                gson.fromJson(
                    it,
                    object : TypeToken<List<CityWeather>>() {}.type
                ) as List<CityWeather>
            } ?: emptyList()

            // Add the new city if it's not already in the list
            val updatedCities = currentCities.toMutableList().apply {
                if (none { it.name == cityWeather.name }) add(cityWeather)
            }

            preferences[CITIES_KEY] = gson.toJson(updatedCities)
        }
    }
}

