package com.weather.app.presentation.bottom.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.weather.app.R
import com.weather.app.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var settingsViewModel: SettingsViewModel
    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        settingsViewModel =
            ViewModelProviders.of(this).get(SettingsViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        settingsViewModel.text.observe(viewLifecycleOwner, Observer {
        })
        return binding.root
    }
}