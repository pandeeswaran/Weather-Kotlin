package com.weather.app.data.modal.nextdayforecast

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Coord {

    @SerializedName("lat")
    @Expose
    var lat: Double? = null

    @SerializedName("lon")
    @Expose
    var lon: Double? = null

}

