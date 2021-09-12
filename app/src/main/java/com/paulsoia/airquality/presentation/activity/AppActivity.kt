package com.paulsoia.airquality.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.paulsoia.airquality.R
import com.paulsoia.airquality.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class AppActivity : AppCompatActivity() {

    private val viewModel: AppViewModel by viewModels()

    private val currentFragment: BaseFragment?
        get() = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as? BaseFragment

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator: Navigator =
        object : AppNavigator(this, R.id.fragmentContainer) {
            override fun setupFragmentTransaction(
                screen: FragmentScreen,
                fragmentTransaction: FragmentTransaction,
                currentFragment: Fragment?,
                nextFragment: Fragment
            ) {
                with(fragmentTransaction) {
                    setReorderingAllowed(true)
                    (nextFragment as? BaseFragment)?.let {
                        when(it.startAnimation) {
                            BaseFragment.StartAnimation.ENTER -> {
                                setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                            }
                            BaseFragment.StartAnimation.SLIDE_UP -> {
                                setCustomAnimations(R.anim.slide_in_down, R.anim.slide_out_up, R.anim.slide_in_up, R.anim.slide_out_down)
                            }
                            BaseFragment.StartAnimation.NO_ANIM -> {

                            }
                        }
                    }
                }
            }

            override fun applyCommands(commands: Array<out Command>) {
                //hide keyboard
                super.applyCommands(commands)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.openStartScreen()
        coldStart()
    }

    private fun coldStart() {
        lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    when(it) {
                        AppViewModel.AppState.Loading -> {}
                        AppViewModel.AppState.MainTabsFlowScreen -> viewModel.navigateToMainScreen()
                        AppViewModel.AppState.NoNetworkError -> {}
                        AppViewModel.AppState.UnexpectedError -> {}
                    }
                }
            }
        }

    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }



}