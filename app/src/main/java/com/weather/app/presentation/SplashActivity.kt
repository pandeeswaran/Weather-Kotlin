package com.weather.app.presentation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.weather.app.R
import com.weather.app.databinding.ActivitySplashBinding
import com.weather.app.presentation.bottom.BottomNavigationActivity
import com.weather.app.presentation.city.CityActivity
import com.weather.app.presentation.mapbox.SearchLocationActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        binding.lifecycleOwner = this

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        splashViewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)
        splashViewModel.initSplashScreen()
        val observer = Observer<String> {
            startActivity(Intent(this, CityActivity::class.java))
            finish()
        }
        splashViewModel.liveData.observe(this, observer)
    }
}