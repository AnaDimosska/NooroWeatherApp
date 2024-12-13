package com.example.noroweatherapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noroweatherapplication.domain.CityPreferences
import com.example.noroweatherapplication.domain.CityWeather
import com.example.noroweatherapplication.domain.GetWeatherUseCase
import com.example.noroweatherapplication.domain.Weather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author: Created by Ana Dimoska
 * @Date: Created on 12/13/2024
 * ViewModel for managing the weather data and user interactions in the Weather app.
 *
 * @property getWeatherUseCase Use case for fetching weather data.
 * @property cityPreferences Preferences for saving and retrieving previously searched cities.
 */

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val cityPreferences: CityPreferences
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState: StateFlow<WeatherUiState> = _uiState

    private var hasNewSearchOccurred = false

    init {
        loadSavedCities()
    }

    fun onQueryChange(newQuery: String) {
        _uiState.value = _uiState.value.copy(query = newQuery)
    }

    fun fetchWeather(city: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            try {
                val weather = getWeatherUseCase(city)

                val cityWeather = CityWeather(
                    name = weather.city,
                    temperature = weather.temperature.toString(),
                    iconUrl = weather.iconUrl
                )

                saveCity(cityWeather)

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    weather = weather
                )

                hasNewSearchOccurred = true
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Failed to fetch weather: ${e.message}"
                )
            }
        }
    }

    private suspend fun saveCity(cityWeather: CityWeather) {
        cityPreferences.saveCity(cityWeather)
    }

    private fun loadSavedCities() {
        viewModelScope.launch {
            cityPreferences.savedCities.collect { cities ->
                _uiState.value = _uiState.value.copy(savedCities = cities)
            }
        }
    }

    fun shouldShowPreviouslySearchedCities(): Boolean {
        return !hasNewSearchOccurred && _uiState.value.savedCities.isNotEmpty()
    }
}


data class WeatherUiState(
    val query: String = "",
    val isLoading: Boolean = false,
    val weather: Weather? = null,
    val error: String? = null,
    val savedCities: List<CityWeather> = emptyList()
)

