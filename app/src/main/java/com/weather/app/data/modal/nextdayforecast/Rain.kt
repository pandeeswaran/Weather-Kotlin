package com.weather.app.data.modal.nextdayforecast

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Rain {

    @SerializedName("3h")
    @Expose
    var _3h: Double? = null

}