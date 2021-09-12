package com.paulsoia.airquality.presentation.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.paulsoia.airquality.R
import com.paulsoia.airquality.presentation.base.BaseFragment
import com.paulsoia.airquality.presentation.navigation.router.FlowRouter
import com.paulsoia.airquality.presentation.utils.setLaunchScreen
import javax.inject.Inject

abstract class FlowFragment : BaseFragment(R.layout.layout_container) {

    private val currentFragment
        get() = childFragmentManager.findFragmentById(R.id.container) as? BaseFragment

    @Inject
    lateinit var cicerone: Cicerone<FlowRouter>

    protected val navigator: Navigator by lazy {
        object : AppNavigator(requireActivity(), R.id.container, childFragmentManager) {
            override fun setupFragmentTransaction(
                screen: FragmentScreen,
                fragmentTransaction: FragmentTransaction,
                currentFragment: Fragment?,
                nextFragment: Fragment
            ) {
                with(fragmentTransaction) {
                    setReorderingAllowed(true)
                    // can set animations for screens
                }
            }

            override fun backToUnexisting(screen: Screen) {
                super.backToUnexisting(screen)
                navigator.setLaunchScreen(screen)
            }

            override fun activityBack() {
                onExit()
            }
        }
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: onExit()
    }

    open fun onExit() {}

    override fun onResume() {
        super.onResume()
        cicerone.getNavigatorHolder().setNavigator(navigator)
    }

    override fun onPause() {
        cicerone.getNavigatorHolder().removeNavigator()
        super.onPause()
    }

}