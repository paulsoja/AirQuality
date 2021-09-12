package com.paulsoia.airquality.presentation.activity

import com.paulsoia.airquality.presentation.base.BaseViewModel
import com.paulsoia.airquality.presentation.navigation.router.AppRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val appRouter: AppRouter,
) : BaseViewModel() {

    internal val state = MutableStateFlow<AppState>(AppState.Loading)

    sealed class AppState {
        object MainTabsFlowScreen : AppState()
        object Loading : AppState()
        object NoNetworkError : AppState()
        object UnexpectedError : AppState()
    }

    internal fun openStartScreen() {
        state.value = AppState.MainTabsFlowScreen
    }

    internal fun navigateToMainScreen() = appRouter.startMainFlow()

}