package com.paulsoia.airquality.presentation.navigation.router

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class FlowRouter @Inject constructor(private val appRouter: Router) : Router() {

    fun forward(screen: Screen) {
        appRouter.navigateTo(screen)
    }

    fun back(screen: Screen) {
        appRouter.backTo(screen)
    }

    fun replace(screen: Screen) {
        appRouter.replaceScreen(screen)
    }

    fun startFlow(vararg screen: Screen) {
        if (screen.size == 1) {
            appRouter.navigateTo(screen[0])
        } else if (screen.size >= 2) {
            appRouter.newChain(*screen)
        }
    }

    fun newRootFlow(screen: Screen) = appRouter.newRootScreen(screen)
    fun finishFlow() = appRouter.exit()

}