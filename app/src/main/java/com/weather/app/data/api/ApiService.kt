package com.weather.app.data.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.weather.app.data.modal.current.WeatherReport
import com.weather.app.data.modal.nextdayforecast.WeatherNextDaysReport

interface ApiService {

    @GET("weather")
    fun getCurrentWeathersReport(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appId: String
    ): Call<WeatherReport>

    @GET("forecast")
    fun getForeCastFiveDaysReport(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appId: String,
        @Query("units") units: String
    ): Call<WeatherNextDaysReport>
}