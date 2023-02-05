package com.example.weatherapp.data.responce

import android.location.Location
import okhttp3.Request


data class CurrentWeatherResponse(
    val current: Current,
    val location: Location,
    val request: Request
)