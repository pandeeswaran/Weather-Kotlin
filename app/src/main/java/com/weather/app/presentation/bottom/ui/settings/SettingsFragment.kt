package com.weather.app.presentation.bottom.ui.settings

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.weather.app.R
import com.weather.app.databinding.FragmentSettingsBinding
import com.weather.app.presentation.bottom.ui.BottomViewModel

class SettingsFragment : Fragment() {

    private lateinit var bottomViewModel: BottomViewModel
    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bottomViewModel = activity?.run {
            ViewModelProviders.of(this).get(BottomViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.help?.setOnClickListener {

        }

        binding.removelocation.setOnClickListener {
            bottomViewModel.deleteAll()
            activity?.setResult(Activity.RESULT_OK, activity?.intent)
            activity?.finish()
        }
    }
}