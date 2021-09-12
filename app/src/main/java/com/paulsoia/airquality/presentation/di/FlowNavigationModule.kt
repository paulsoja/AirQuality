package com.paulsoia.airquality.presentation.di

import com.github.terrakok.cicerone.Cicerone
import com.paulsoia.airquality.presentation.navigation.router.AppRouter
import com.paulsoia.airquality.presentation.navigation.router.FlowRouter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FlowNavigationModule {

    @Provides
    @Singleton
    fun provideCicerone(appRouter: AppRouter) = Cicerone.create(FlowRouter(appRouter))

    @Provides
    @Singleton
    fun provideRouter(cicerone: Cicerone<FlowRouter>): FlowRouter = cicerone.router

}