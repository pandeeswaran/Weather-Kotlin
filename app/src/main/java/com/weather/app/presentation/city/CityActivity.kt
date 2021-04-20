package com.weather.app.presentation.city

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.weather.app.R
import com.weather.app.data.entity.LocationEntity
import com.weather.app.data.room.AppDatabase
import com.weather.app.databinding.ActivityCityBinding
import com.weather.app.presentation.bottom.BottomNavigationActivity
import com.weather.app.presentation.mapbox.SearchLocationActivity


class CityActivity : AppCompatActivity() {
    private lateinit var getActivity: CityActivity
    private lateinit var binding: ActivityCityBinding
    private lateinit var adapter: CityAdapter
    private lateinit var viewModel: CityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActivity = this
        binding = DataBindingUtil.setContentView(getActivity, R.layout.activity_city)
        val appDatabase = AppDatabase.getDatabase(getActivity)

        var factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return CityViewModel(appDatabase) as T
            }
        }

        viewModel = ViewModelProviders.of(getActivity, factory).get(CityViewModel::class.java)

        binding.fabAddLocation.setOnClickListener {
            startActivityForResult(Intent(getActivity, SearchLocationActivity::class.java), 100)
        }
        getLocationFromDB()
    }

    private fun getLocationFromDB() {
        viewModel.getBookMarkedLocations().observe(this, { it ->
            if (it.isNotEmpty()) {
                binding.tvNoData.visibility = View.GONE
                binding.rcvLocation.visibility = View.VISIBLE

                binding.rcvLocation.adapter =
                    CityAdapter(it) { locationEntity: LocationEntity, i: Int ->
                        partItemClicked(
                            locationEntity, i
                        )
                    }
            } else {
                binding.tvNoData.visibility = View.VISIBLE
                binding.rcvLocation.visibility = View.GONE
            }
        })
    }

    private fun partItemClicked(it: LocationEntity, id: Int) {
        Log.e("it", "" + it.placeName)

        if (id == 0) {
            viewModel.selectedEntity(it)
            startActivityForResult(
                Intent(getActivity, BottomNavigationActivity::class.java).putExtra("id", it.id), 100
            )
        } else {
            Log.e("delete", "" + it.placeName)
            viewModel.deleteById(it)
            viewModel.refresh()
            binding.rcvLocation.adapter?.notifyDataSetChanged()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100) {
            if (resultCode == Activity.RESULT_OK) {
                viewModel.refresh()
                getLocationFromDB()
            }
        }
    }

}