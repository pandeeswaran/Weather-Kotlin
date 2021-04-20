package com.weather.app.presentation.bottom.ui.weekly

import android.annotation.SuppressLint
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.weather.app.data.modal.nextdayforecast.ListData
import com.weather.app.databinding.ItemNextForecastBinding
import java.text.ParseException
import java.text.SimpleDateFormat

class ForcastListAdapter(
    private var listDataList: List<ListData>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNextForecastBinding.inflate(inflater, parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as WeatherViewHolder).bind(listDataList[position], position)
    }

    override fun getItemCount(): Int = listDataList.size

    inner class WeatherViewHolder(private val binding: ItemNextForecastBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(entity: ListData, position: Int) {
            if (position != 0) {

                binding.ivWeatherStatus
                    .load("http://openweathermap.org/img/wn/" + entity.weather[0].icon + "@2x.png")

                binding.tvDescription.text = entity.weather[0].description

                if (position == 1) {
                    binding.tvDate.text = "Tommorrow"
                } else {
                    binding.tvDate.text = dateConvert(entity.getDtTxt()!!)
                }
                binding.tvDayName.text = dayConvert(entity.getDtTxt()!!)
                binding.tvTemp.text =
                    String.format(
                        "%d%s",
                        (entity.main?.temp?.minus(273.15))?.toInt(),
                        Html.fromHtml(" \u2103")
                    )
            }
        }

        //convert date format in dd-MM
        @SuppressLint("SimpleDateFormat")
        private fun dateConvert(date: String): String {
            var dateString = date
            try {

                val sdf2 = SimpleDateFormat("dd-MM")
                val sdf = SimpleDateFormat("yyyy-MM-dd")
                dateString = sdf2.format(sdf.parse(dateString))

            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return dateString
        }

        //convert date format in day name
        @SuppressLint("SimpleDateFormat")
        private fun dayConvert(dateString: String): String? {

            val sdf = SimpleDateFormat("yyyy-MM-dd")


            val sdf_ = SimpleDateFormat("EEEE")

            var dayName: String? = null
            try {
                dayName = sdf_.format(sdf.parse(dateString))
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return dayName
        }
    }
}