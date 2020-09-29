package com.javadsh98.datastorepractice

import android.content.Context
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.preferencesKey

class SettingManager(context: Context){

    val dataStore = context.createDataStore("javad_setting")



    companion object{
        val NIGHT_KEY = preferencesKey<Boolean>("dark_mode")
    }
}