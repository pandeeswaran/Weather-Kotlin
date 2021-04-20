package com.weather.app.presentation.mapbox

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weather.app.data.entity.LocationEntity
import com.weather.app.data.room.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationViewModel : ViewModel() {

    fun insert(appDatabase: AppDatabase, locationEntity: LocationEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            appDatabase.locationEntity().insertAll(locationEntity)
        }

    fun getAll(appDatabase: AppDatabase) = viewModelScope.launch(Dispatchers.IO) {
        appDatabase.locationEntity().getAll().forEach {
            Log.e("Fetch Records", "Id:  : ${it.id}")
            Log.e("Fetch Records", "Name:  : ${it.placeName}")
        }
    }
}