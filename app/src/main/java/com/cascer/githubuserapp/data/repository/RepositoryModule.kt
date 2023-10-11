package com.cascer.githubuserapp.data.repository

import com.cascer.githubuserapp.data.api.ApiService
import com.cascer.githubuserapp.data.db.UserDao
import com.cascer.githubuserapp.di.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRepositoryImpl(
        retrofit: Retrofit,
        userDao: UserDao,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): Repository {
        return RepositoryImpl(retrofit.create(ApiService::class.java), userDao, ioDispatcher)
    }
}