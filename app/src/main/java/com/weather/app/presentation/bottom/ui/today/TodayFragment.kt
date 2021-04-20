package com.weather.app.presentation.bottom.ui.today

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import coil.load
import com.weather.app.R
import com.weather.app.databinding.FragmentHomeBinding
import com.weather.app.presentation.bottom.ui.BottomViewModel
import com.weather.app.data.modal.current.WeatherReport
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

class TodayFragment : Fragment() {

    private lateinit var homeViewModel: BottomViewModel
    private lateinit var binding: FragmentHomeBinding
    private var latitude by Delegates.notNull<Double>()
    private var longtitude by Delegates.notNull<Double>()
    private val appId: String = "fae7190d7e6433ec3a45285ffcf55c86"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = activity?.run {
            ViewModelProviders.of(this).get(BottomViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val currentDate = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(Date())

        binding.dateTimeText.text = currentDate
        homeViewModel.selectedEntity.observe(viewLifecycleOwner, {
            binding.locationText.text = it.placeName.toString()
            latitude = it.latitude!!
            longtitude = it.longitude!!

            getWeatherReport()
        })
        return binding.root
    }

    private fun getWeatherReport() {
        homeViewModel.getCurrentWeatherReport(latitude.toString(), longtitude.toString(), appId)
            .observe(viewLifecycleOwner,
                {
                    if (it == null) return@observe
                    Log.e("it", "" + it)
                    setData(it)
                }
            )
    }

    private fun setData(weatherReport: WeatherReport) {
        val currentTempKelvin = weatherReport?.main?.temp
        val currentTempCelsius = currentTempKelvin?.minus(273.15)

        binding.temperatureText.text = currentTempCelsius?.toInt().toString()
        binding.humidityText.text = "" + weatherReport?.main?.humidity + " %"
        binding.sensibleText?.text = "" + (weatherReport?.main?.feels_like?.minus(273.15))?.toInt()
            .toString() + Html.fromHtml(" \u2103")
        binding.pressureText.text = "" + weatherReport?.main?.pressure + " hPa"
        binding.windSpeedText.text = "" + weatherReport?.wind?.speed + " km/h"

        binding.weatherDescriptionText.text = weatherReport.weather?.get(0)?.description
        binding.weatherIndicatorImage.load("http://openweathermap.org/img/wn/" + weatherReport.weather?.get(0)?.icon + "@2x.png")

        binding.realFeelText.text =
            weatherReport?.main?.tempMax?.minus(273.15)?.toInt()
                .toString() + " / " + weatherReport?.main?.tempMin?.minus(
                273.15
            )?.toInt().toString()

    }
}