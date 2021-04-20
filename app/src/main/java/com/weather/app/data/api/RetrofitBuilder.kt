package com.weather.app.data.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val BASE_URL = "http://api.openweathermap.org/data/2.5/"

    private val gson: Gson by lazy {
        GsonBuilder().setLenient().create()
    }

    private val interceptor: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)

}