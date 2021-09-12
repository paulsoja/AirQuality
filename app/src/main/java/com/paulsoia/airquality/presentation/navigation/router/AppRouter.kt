package com.paulsoia.airquality.presentation.navigation.router

import com.github.terrakok.cicerone.Router
import com.paulsoia.airquality.presentation.navigation.Screens
import javax.inject.Inject

class AppRouter @Inject constructor() : Router() {

    fun startMainFlow() {
        newRootScreen(Screens.mainTabsFlowScreen())
    }

}