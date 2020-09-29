package com.javadsh98.datastorepractice

import android.content.Context
import android.graphics.LightingColorFilter
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey

enum class ThemeMode {
    LIGHT, DARK
}

class SettingManager(context: Context) {

    val dataStore = context.createDataStore("javad_setting")

    suspend fun setTheme(themeMode: ThemeMode) {
        dataStore.edit { preference ->
            preference[NIGHT_KEY] = when (themeMode) {
                ThemeMode.DARK -> true
                ThemeMode.LIGHT -> false
            }

        }
    }

    companion object {
        val NIGHT_KEY = preferencesKey<Boolean>("dark_mode")
    }
}