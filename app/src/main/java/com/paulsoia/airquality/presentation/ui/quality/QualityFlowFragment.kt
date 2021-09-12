package com.paulsoia.airquality.presentation.ui.quality

import android.os.Bundle
import com.paulsoia.airquality.presentation.navigation.FlowFragment
import com.paulsoia.airquality.presentation.navigation.Screens
import com.paulsoia.airquality.presentation.navigation.router.FlowRouter
import com.paulsoia.airquality.presentation.utils.setLaunchScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class QualityFlowFragment : FlowFragment() {

    companion object {
        fun newInstance() = QualityFlowFragment()
    }

    @Inject lateinit var router: FlowRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator.setLaunchScreen(Screens.qualityScreen())
    }

    override fun onExit() = router.finishFlow()

}