package com.example.countrydemo.util

interface ILocalStorage {
    suspend fun saveData(value: String)
    suspend fun getData(defaultValue: String=""):String
    suspend fun isLocalDataExists():Boolean
}