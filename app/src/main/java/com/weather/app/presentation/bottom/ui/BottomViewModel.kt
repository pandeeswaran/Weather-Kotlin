package com.weather.app.presentation.bottom.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weather.app.data.api.ApiService
import com.weather.app.data.api.RetrofitBuilder
import com.weather.app.data.entity.LocationEntity
import com.weather.app.data.modal.current.WeatherReport
import com.weather.app.data.modal.nextdayforecast.WeatherNextDaysReport
import com.weather.app.data.room.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BottomViewModel(private var appDatabase: AppDatabase, private val id: Int) : ViewModel() {

    init {
        getSelectedEntity(id)
    }

    var selectedEntity = MutableLiveData<LocationEntity>()

    private fun getSelectedEntity(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        selectedEntity.postValue(appDatabase.locationEntity().loadSingleEntity(id))
        Log.e("entity", "" + (selectedEntity.value?.placeName ?: ""))
    }

    fun selectedEntityPlace(entity: LocationEntity) {
        selectedEntity.value = entity;
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        appDatabase.locationEntity().deleteAll()
    }

    private val apiRequest: ApiService = RetrofitBuilder.apiService


    fun getCurrentWeatherReport(lat: String, lon: String, appId: String): LiveData<WeatherReport> {


        Log.d("TAG", "onResponse response:: $lat  $lon $appId")
        val data = MutableLiveData<WeatherReport>()
        apiRequest.getCurrentWeathersReport(lat, lon, appId)
            .enqueue(object : Callback<WeatherReport> {

                override fun onResponse(
                    call: Call<WeatherReport>,
                    response: Response<WeatherReport>
                ) {
                    //   Log.d(TAG, "onResponse response:: ${response.isSuccessful}")
                    /*     Log.d(TAG, "onResponse response:: ${Gson().toJson(response)}")
     */


                    if (response.body() != null) {
                        data.value = response.body()
                        //     Log.d(TAG, "onResponse response:: ${data.value?.main?.temp}")
                    }
                }

                override fun onFailure(call: Call<WeatherReport>, t: Throwable) {
//                    data.value=null
                }
            })
        return data
    }

    fun getForecastWeatherReport(
        lat: String,
        lon: String,
        appId: String
    ): LiveData<WeatherNextDaysReport> {
        val data = MutableLiveData<WeatherNextDaysReport>()
        apiRequest.getForeCastFiveDaysReport(lat, lon, appId, "metric")
            .enqueue(object : Callback<WeatherNextDaysReport> {

                override fun onResponse(
                    call: Call<WeatherNextDaysReport>,
                    response: Response<WeatherNextDaysReport>
                ) {
                    //   Log.d("TAG", "onResponse response:: ${Gson().toJson(response)}")
                    if (response.body() != null) {
                        data.value = response.body()
                    }
                }

                override fun onFailure(call: Call<WeatherNextDaysReport>, t: Throwable) {
                    // data.value=null
                }
            })
        return data
    }
}