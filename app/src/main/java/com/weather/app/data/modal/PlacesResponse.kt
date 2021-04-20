package com.weather.app.data.modal

import com.google.gson.annotations.SerializedName

data class PlacesResponse(

	@field:SerializedName("coordinates")
	val coordinates: List<Double?>? = null,

	@field:SerializedName("type")
	val type: String? = null
)
