package com.javadsh98.datastorepractice

import android.graphics.ColorFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_preference.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PreferenceActivity : AppCompatActivity() {

    val settingManager by lazy {
        SettingManager(this)
    }
    var dark = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference)

        setTheme()
        observeNight()
    }

    private fun setTheme() {
        imageView.setOnClickListener {
            lifecycleScope.launch {
                when(dark){
                    true -> {
                        dark = false
                        settingManager.setTheme(ThemeMode.DARK)
                    }

                    false -> {
                        dark = true
                        settingManager.setTheme(ThemeMode.LIGHT)
                    }
                }
            }
        }
    }

    private fun observeNight() {

        lifecycleScope.launch {
            settingManager.getTheme.collect {
                when(it){
                    ThemeMode.LIGHT -> {
                        setLight()
                    }
                    ThemeMode.DARK -> {
                        setNight()
                    }
                }
            }
        }
    }

    private fun setLight() {
        constraintlayout_preference.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white))
        imageView.setImageResource(R.drawable.ic_baseline_wb_sunny_24)
    }

    private fun setNight() {
        constraintlayout_preference.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray))
        imageView.setImageResource(R.drawable.moon)
    }


}