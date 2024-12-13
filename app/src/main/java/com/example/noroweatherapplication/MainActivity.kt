package com.example.noroweatherapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.noroweatherapplication.presentation.WeatherScreen
import com.example.noroweatherapplication.presentation.WeatherViewModel
import com.example.noroweatherapplication.ui.theme.NoroWeatherApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the content of the activity
        setContent {
            NoroWeatherApplicationTheme {
                // Use Hilt to get an instance of the WeatherViewModel
                val weatherViewModel: WeatherViewModel = hiltViewModel()
                WeatherScreen(viewModel = weatherViewModel)

                // Pass the ViewModel to the WeatherScreen
                WeatherScreen(viewModel = weatherViewModel)
            }
        }
    }
}