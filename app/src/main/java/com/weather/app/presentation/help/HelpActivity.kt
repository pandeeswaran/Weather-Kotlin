package com.weather.app.presentation.help

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.weather.app.R
import com.weather.app.databinding.ActivityHelpBinding

class HelpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHelpBinding
    private lateinit var getActivity: HelpActivity

    private val loadData = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "<title>Page Title</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "\n" +
            "<h1>WeatherApp-Kotlin</h1>\n" +
            "<p>Retrieve Weather Information from CurrentWeatherData API to show an example concepts of MVVM and Kotlin</p>\n" +
            "\n" +
            "<p>The weather information retrieved from https://openweathermap.org/api  CurrentWeatherData API and Display the data in a list.</p>\n" +
            "\n" +
            "<p><pre>1. Compatibility with Android 5.1 and onwards \n" +
            "2. Code quality, readability and consistent code style\n" +
            "3. Best UI practices (Material design)\n" +
            "4. Room Database (SQLite)\n" +
            "5. Retrofit\n" +
            "6. Android Architecture Components\n" +
            "\n" +
            "<h1>WorkFlow</h1>\n" +
            "    1. Fab \"Add\" button navigate to Map view activity, in this screen user can able to\tsearch their desired location and its automatically stored into the database for your future reference\n" +
            "    2. Selected location should be displayed as a list\n" +
            "    3. Provide the options for add and delete location\n" +
            "    4. User click the location it will navigate to weather information screen\n" +
            "\n" +
            "</body>\n" +
            "</html>"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getActivity = this
        binding = DataBindingUtil.setContentView(getActivity, R.layout.activity_help)
        binding.lifecycleOwner = getActivity

        binding.webview.webViewClient = WebViewClient()

        binding.webview.loadData(loadData, "text/html", "UTF-8")

        binding.webview.settings.javaScriptEnabled = true

        binding.webview.settings.setSupportZoom(true)
    }
}