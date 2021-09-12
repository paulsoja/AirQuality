package com.paulsoia.airquality.presentation.ui

import android.os.Bundle
import com.paulsoia.airquality.R
import com.paulsoia.airquality.presentation.base.BaseFragment
import com.paulsoia.airquality.presentation.ui.quality.QualityFlowFragment
import com.paulsoia.airquality.presentation.ui.settings.SettingsFlowFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainTabsFlowFragment : BaseFragment(R.layout.fragment_main_tabs) {

    companion object {
        fun newInstance() = MainTabsFlowFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val settings = SettingsFlowFragment.newInstance()
        val quality = QualityFlowFragment.newInstance()

        childFragmentManager.beginTransaction().apply {
            add(R.id.mainScreenContainer, settings, "settings_flow").hide(settings)
            add(R.id.mainScreenContainer, quality, "quality_flow")
        }.commit()
    }

}