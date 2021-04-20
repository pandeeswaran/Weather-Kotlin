package com.weather.app.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    var liveData: MutableLiveData<String> = MutableLiveData()

    fun initSplashScreen() {
        viewModelScope.launch {
            delay(2000)
            var splashModel = "success"
            liveData.value = splashModel;
        }
    }
}