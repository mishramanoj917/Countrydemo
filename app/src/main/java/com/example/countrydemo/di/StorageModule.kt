package com.example.countrydemo.di

import com.example.countrydemo.util.ILocalStorage
import com.example.countrydemo.util.LocalStorage
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class StorageModule {
    @Binds
    abstract fun providesPreferenceStorage(
        appPreferenceStorage: LocalStorage
    ): ILocalStorage

}