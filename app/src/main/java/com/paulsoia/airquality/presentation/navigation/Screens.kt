package com.paulsoia.airquality.presentation.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.paulsoia.airquality.presentation.ui.MainTabsFlowFragment
import com.paulsoia.airquality.presentation.ui.quality.QualityFlowFragment
import com.paulsoia.airquality.presentation.ui.quality.QualityFragment
import com.paulsoia.airquality.presentation.ui.settings.SettingsFlowFragment
import com.paulsoia.airquality.presentation.ui.settings.SettingsFragment

object Screens {

    fun mainTabsFlowScreen() = FragmentScreen { MainTabsFlowFragment.newInstance() }

    //quality flow
    fun qualityFlowScreen() = FragmentScreen { QualityFlowFragment.newInstance() }

    fun qualityScreen() = FragmentScreen { QualityFragment.newInstance() }

    //settings flow
    fun settingsFlowScreen() = FragmentScreen { SettingsFlowFragment.newInstance() }

    fun settingsScreen() = FragmentScreen { SettingsFragment.newInstance() }

}