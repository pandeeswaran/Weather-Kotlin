package com.weather.app.presentation.bottom.ui.weekly

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.weather.app.R
import com.weather.app.databinding.FragmentHelpBinding
import com.weather.app.presentation.bottom.ui.BottomViewModel
import com.weather.app.data.modal.nextdayforecast.ListData
import com.weather.app.data.modal.nextdayforecast.WeatherNextDaysReport
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

class WeeklyReportFragment : Fragment() {

    private lateinit var bottomViewModel: BottomViewModel
    private lateinit var binding: FragmentHelpBinding

    private var latitude by Delegates.notNull<Double>()
    private var longtitude by Delegates.notNull<Double>()
    private val appId: String = "fae7190d7e6433ec3a45285ffcf55c86"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bottomViewModel = activity?.run {
            ViewModelProviders.of(this).get(BottomViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_help, container, false)

        val currentDate = SimpleDateFormat("MMM dd, yyyy hh:mm:ss", Locale.getDefault()).format(Date())
        binding.dateTimeText.text = currentDate
        bottomViewModel.selectedEntity.observe(viewLifecycleOwner, {
            binding.locationText.text = it.placeName.toString()
            latitude = it.latitude!!
            longtitude = it.longitude!!

            getWeatherReport()
        })
        return binding.root
    }

    private fun getWeatherReport() {
        bottomViewModel.getForecastWeatherReport(latitude.toString(), longtitude.toString(), appId)
            .observe(viewLifecycleOwner,
                {
                    if (it == null) return@observe
                    Log.e("it", "" + it)
                    setData(it)
                }
            )
    }

    private fun setData(weatherNextDaysReport: WeatherNextDaysReport) {
        val stringListDataHashMap = HashMap<String, ListData>()
        if (weatherNextDaysReport.list.isEmpty()) {

        } else {
            for (y in weatherNextDaysReport.list.indices) {
                stringListDataHashMap[weatherNextDaysReport.list[y].getDtTxt()!!] =
                    weatherNextDaysReport.list[y]
            }

            val listData = ArrayList<ListData>()

            for (value in stringListDataHashMap.values) {
                println("Ascending before Value = " + value.getDtTxt())
                listData.add(value)
            }

            // Sort date in assending order
            Collections.sort(listData, object : Comparator<ListData> {
                @SuppressLint("SimpleDateFormat")
                var f: DateFormat = SimpleDateFormat("yyyy-MM-dd")

                override fun compare(lhs: ListData, rhs: ListData): Int {
                    try {
                        return f.parse(lhs.getDtTxt()).compareTo(f.parse(rhs.getDtTxt()))
                    } catch (e: ParseException) {
                        e.printStackTrace()
                        throw IllegalArgumentException(e)
                    }

                }
            })
            for (i in listData.indices) {
                println("Ascending after Value = " + listData[i].getDtTxt())
            }
            binding.rvNextDays.adapter = ForcastListAdapter(listData)
        }
    }
}