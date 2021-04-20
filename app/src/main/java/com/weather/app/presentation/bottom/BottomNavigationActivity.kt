package com.weather.app.presentation.bottom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.weather.app.R
import com.weather.app.data.room.AppDatabase
import com.weather.app.presentation.bottom.ui.BottomViewModel
import kotlin.properties.Delegates

class BottomNavigationActivity : AppCompatActivity() {

    private lateinit var getActivity: BottomNavigationActivity
    private lateinit var bottomViewModel: BottomViewModel
    private var id by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActivity = this
        setTheme(R.style.AppNoActionTheme)
        setContentView(R.layout.activity_bottom_navigation)

        val appDatabase = AppDatabase.getDatabase(getActivity)

        id = intent.getIntExtra("id", 0)

        var factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return BottomViewModel(appDatabase, id) as T
            }
        }

        bottomViewModel =
            ViewModelProviders.of(getActivity, factory).get(BottomViewModel::class.java)


        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)

        navView.setupWithNavController(navController)
    }
}