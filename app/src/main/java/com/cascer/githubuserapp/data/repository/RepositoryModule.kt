package com.cascer.githubuserapp.data.repository

import com.cascer.githubuserapp.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRepositoryImpl(
        retrofit: Retrofit
    ): Repository {
        return RepositoryImpl(retrofit.create(ApiService::class.java))
    }
}