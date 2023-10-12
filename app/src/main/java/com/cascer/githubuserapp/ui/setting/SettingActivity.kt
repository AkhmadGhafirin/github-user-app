package com.cascer.githubuserapp.ui.setting

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.cascer.githubuserapp.databinding.ActivitySettingBinding
import com.cascer.githubuserapp.ui.list.MainViewModel
import com.cascer.githubuserapp.utils.dataStore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        application.dataStore
        binding.swDarkMode.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewModel.saveThemeSetting(true)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.swDarkMode.isChecked = true
            } else {
                viewModel.saveThemeSetting(false)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.swDarkMode.isChecked = false
            }
        }
        viewModel.getThemeSettings().observe(this@SettingActivity) { isDarkMode ->
            if (isDarkMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.swDarkMode.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.swDarkMode.isChecked = false
            }
        }
    }
}