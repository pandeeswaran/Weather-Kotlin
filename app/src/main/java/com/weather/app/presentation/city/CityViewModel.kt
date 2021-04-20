package com.weather.app.presentation.city

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weather.app.data.entity.LocationEntity
import com.weather.app.data.room.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CityViewModel(private var appDatabase: AppDatabase) : ViewModel() {

    private var locationEntity = MutableLiveData<List<LocationEntity>>()
    var selectedEntity = MutableLiveData<LocationEntity>()

    init {
        getAll()
    }

    private fun getAll() = viewModelScope.launch(Dispatchers.IO) {
        locationEntity.postValue(appDatabase.locationEntity().getAll())
        appDatabase.locationEntity().getAll().forEach {
            // locationEntity.postValue(it)
            Log.e("Fetch Records", "Id:  : ${it.id}")
            Log.e("Fetch Records", "Name:  : ${it.placeName}")
        }

    }

    fun deleteById(id: LocationEntity) = viewModelScope.launch(Dispatchers.IO) {
        appDatabase.locationEntity().deleteByObject(id)
    }

    fun refresh() = viewModelScope.launch(Dispatchers.IO) {
        locationEntity.postValue(appDatabase.locationEntity().getAll())
        appDatabase.locationEntity().getAll().forEach {
            // locationEntity.postValue(it)
            Log.e("Refersh Records", "Id:  : ${it.id}")
            Log.e("Fetch Records", "Name:  : ${it.placeName}")
        }
    }

    fun getBookMarkedLocations(): LiveData<List<LocationEntity>> {
        return locationEntity
    }

    fun selectedEntity(entity: LocationEntity) {
        selectedEntity.value = entity;
    }
}