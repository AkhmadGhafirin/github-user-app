package com.cascer.githubuserapp.di

import android.content.Context
import com.cascer.githubuserapp.data.db.UserDao
import com.cascer.githubuserapp.data.db.UserDatabase
import com.cascer.githubuserapp.utils.SettingPreferences
import com.cascer.githubuserapp.utils.dataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideUserDatabase(@ApplicationContext context: Context): UserDatabase {
        return UserDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideSettingPreferences(@ApplicationContext context: Context): SettingPreferences {
        return SettingPreferences.getInstance(context.dataStore)
    }

    @Singleton
    @Provides
    fun provideUserDao(database: UserDatabase): UserDao = database.dao()

    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}