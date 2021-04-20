package com.weather.app.presentation.city

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.weather.app.data.entity.LocationEntity
import com.weather.app.databinding.RowCityLayoutBinding

class CityAdapter(
    private var locationEntity: List<LocationEntity>,
    private val clickListener: (LocationEntity) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowCityLayoutBinding.inflate(inflater, parent, false)
        return CityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CityViewHolder).bind(locationEntity[position], clickListener)
    }

    override fun getItemCount(): Int = locationEntity.size

    inner class CityViewHolder(private val binding: RowCityLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(entity: LocationEntity, clickListener: (LocationEntity) -> Unit) {
            binding.tvPlaceName.text = entity.placeName
            binding.tvLatitude.text = "Latitude: " + entity.latitude.toString()
            binding.tvLongitude.text = "Longitude: " + entity.longitude.toString()
            binding.root.setOnClickListener { clickListener(entity) }
        }
    }
}