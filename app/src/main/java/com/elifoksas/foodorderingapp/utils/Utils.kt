package com.elifoksas.foodorderingapp.utils

import android.content.Context
import android.content.SharedPreferences

object Utils {
    private lateinit var prefs: SharedPreferences
    private lateinit var prefsEdit: SharedPreferences.Editor
    fun init(context: Context) {
        try {

            prefs = context.getSharedPreferences("elif_oksas",Context.MODE_PRIVATE)
            prefsEdit = prefs.edit()

        }
        catch (e:Exception){
            throw Exception("SharedPrefsHelper init failed")
        }
    }
    fun getString(key: String, defaultValue: String): String {
        return prefs.getString(key, defaultValue).toString()
    }
}