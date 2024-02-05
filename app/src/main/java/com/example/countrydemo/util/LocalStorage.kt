package com.example.countrydemo.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalStorage @Inject constructor(
    @ApplicationContext context: Context
):ILocalStorage {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    override suspend fun saveData(value: String) {
        Log.d("LocalStorage", value)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.putString(Constant.COUNTRY_JSON, value)
        editor.apply()
    }

    override suspend fun getData(defaultValue: String): String {
        return sharedPreferences.getString(Constant.COUNTRY_JSON, defaultValue) ?: defaultValue
    }

    override suspend fun isLocalDataExists(): Boolean {
        val stringValue = sharedPreferences.getString(Constant.COUNTRY_JSON, "") ?: ""
        return stringValue.isNotEmpty()
    }
}