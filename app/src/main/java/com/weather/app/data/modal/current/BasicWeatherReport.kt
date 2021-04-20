package com.weather.app.data.modal.current

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BasicWeatherReport {

    @SerializedName("type")
    @Expose
    var type: Int? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("message")
    @Expose
    var message: Double? = null

    @SerializedName("country")
    @Expose
    var country: String? = null

    @SerializedName("sunrise")
    @Expose
    var sunrise: Long? = null

    @SerializedName("sunset")
    @Expose
    var sunset: Long? = null

}