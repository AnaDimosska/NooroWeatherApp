package com.example.noroweatherapplication.di

import android.content.Context
import com.example.noroweatherapplication.data.api.WeatherApiService
import com.example.noroweatherapplication.data.repo.WeatherRepositoryImpl
import com.example.noroweatherapplication.domain.CityPreferences
import com.example.noroweatherapplication.domain.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * @author: Created by Ana Dimoska
 * @Date: Created on 12/13/2024
 * AppModule is a Dagger module responsible for providing the dependencies for the weather application.
 * This module includes the setup of Retrofit for network communication, the WeatherApiService for fetching
 * weather data, and the WeatherRepository for managing weather data operations.
 * It is installed in the [SingletonComponent] to ensure a single instance is provided across the app.
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApiKey(): String = "7c96051484cd47669c1163029241112"

    @Provides
    @Singleton
    fun provideCityPreferences(@ApplicationContext context: Context): CityPreferences {
        return CityPreferences(context)
    }

    @Provides
    @Singleton
    fun provideWeatherApiService(): WeatherApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(
        apiService: WeatherApiService,
        apiKey: String
    ): WeatherRepository = WeatherRepositoryImpl(apiService, apiKey)
}
