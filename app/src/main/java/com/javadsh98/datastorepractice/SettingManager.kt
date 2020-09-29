package com.javadsh98.datastorepractice

import android.content.Context
import android.graphics.LightingColorFilter
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.emptyPreferences
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

enum class ThemeMode {
    LIGHT, DARK
}

class SettingManager(context: Context) {

    val dataStore = context.createDataStore("javad_setting")

    val getTheme: Flow<ThemeMode> = dataStore.data
        .catch {
            if (it is IOException){
                it.printStackTrace()
                emit(emptyPreferences())
            }else{
                throw it
            }
        }
        .map {
            val pref = it[NIGHT_KEY] ?: false
            when(pref){
                true -> ThemeMode.DARK
                false -> ThemeMode.LIGHT
            }
        }

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