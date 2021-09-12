package com.paulsoia.airquality.presentation.di

import android.app.Application
import com.google.gson.GsonBuilder
import com.mocklets.pluto.PlutoInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        private const val COMMON_TIMEOUT = 15L
        const val CONNECT_TIMEOUT = COMMON_TIMEOUT
        const val READ_TIMEOUT = COMMON_TIMEOUT
        const val WRITE_TIMEOUT = COMMON_TIMEOUT
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        gsonBuilder: GsonBuilder,
        okHttpClient: OkHttpClient,
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
        .baseUrl("https://api.waqi.info/")
        .build()

    @Provides
    @Singleton
    fun provideGsonBuilder(): GsonBuilder = GsonBuilder()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        cache: Cache,
    ): OkHttpClient = OkHttpClient.Builder().apply {
        cache(cache)
        connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        addInterceptors()
    }.build()

    @Provides
    @Singleton
    fun providesOkHttpCache(application: Application): Cache = Cache(
        File(application.cacheDir, "http-cache"), (15 * 1024 * 1024).toLong() /* 15 MB */)

    private fun OkHttpClient.Builder.addInterceptors(): OkHttpClient.Builder {
//    addInterceptor(GzipRequestInterceptor())
        addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        addInterceptor(PlutoInterceptor())
        return this
    }

}