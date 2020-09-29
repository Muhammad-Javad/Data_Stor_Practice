package com.javadsh98.datastorepractice

import android.content.Context
import androidx.datastore.preferences.createDataStore

class SettingManager(context: Context){

    val dataStore = context.createDataStore("javad_setting")

}