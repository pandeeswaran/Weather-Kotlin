package com.weather.app.data.modal.nextdayforecast

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WeatherNextDaysReport {

    @SerializedName("cod")
    @Expose
    var cod: String? = null

    @SerializedName("message")
    @Expose
    var message: Int? = null

    @SerializedName("cnt")
    @Expose
    var cnt: Int? = null

    @SerializedName("list")
    @Expose
    var list: List<ListData> = ArrayList()

    @SerializedName("city")
    @Expose
    var city: City? = null

}

/* */