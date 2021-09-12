package com.paulsoia.airquality.presentation.ui.settings

import android.os.Bundle
import com.paulsoia.airquality.presentation.navigation.FlowFragment
import com.paulsoia.airquality.presentation.navigation.Screens
import com.paulsoia.airquality.presentation.navigation.router.FlowRouter
import com.paulsoia.airquality.presentation.utils.setLaunchScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFlowFragment : FlowFragment() {

    companion object {
        fun newInstance() = SettingsFlowFragment()
    }

    @Inject lateinit var router: FlowRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator.setLaunchScreen(Screens.settingsScreen())
    }

    override fun onExit() = router.finishFlow()

}