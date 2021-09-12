package com.paulsoia.airquality.presentation.di

import com.github.terrakok.cicerone.Cicerone
import com.paulsoia.airquality.presentation.navigation.router.AppRouter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NavigationModule {

    @Provides
    @Singleton
    fun provideCicerone() = Cicerone.create(AppRouter())

    @Provides
    @Singleton
    fun provideRouter(cicerone: Cicerone<AppRouter>): AppRouter = cicerone.router

    @Provides
    @Singleton
    fun provideNavigatorHolder(cicerone: Cicerone<AppRouter>) = cicerone.getNavigatorHolder()

}