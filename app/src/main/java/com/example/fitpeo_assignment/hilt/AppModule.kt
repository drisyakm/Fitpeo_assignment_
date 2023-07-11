package com.example.fitpeo_assignment.hilt

import com.example.fitpeo_assignment.retrofit.ApiClient
import com.example.fitpeo_assignment.retrofit.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    internal fun provideApiInterface(): ApiInterface {
        return ApiClient.buildService(ApiInterface::class.java)
    }
}