package com.weather.app.data

interface ApiHelper {

    suspend fun getCurrentWeathersReport()

    suspend fun getForeCastFiveDaysReport()
}