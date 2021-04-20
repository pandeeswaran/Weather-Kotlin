package com.weather.app.data.modal.nextdayforecast

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ListData {

    @SerializedName("dt")
    @Expose
    var dt: Int? = null

    @SerializedName("main")
    @Expose
    var main: Main? = null

    @SerializedName("weather")
    @Expose
    var weather: List<Weather> = ArrayList<Weather>()

    @SerializedName("clouds")
    @Expose
    var clouds: Clouds? = null

    @SerializedName("wind")
    @Expose
    var wind: Wind? = null

    @SerializedName("sys")
    @Expose
    var sys: Sys? = null

    @SerializedName("dt_txt")
    @Expose
    private var dtTxt: String? = null

    @SerializedName("rain")
    @Expose
    var rain: Rain? = null

    fun getDtTxt(): String? {
        val date = dtTxt!!.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        this.dtTxt = date[0]

        return dtTxt
    }

    fun setDtTxt(dtTxt: String) {
        val date = dtTxt.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        this.dtTxt = date[0]
    }

}