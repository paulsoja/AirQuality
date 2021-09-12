package com.paulsoia.airquality.presentation.base

import androidx.fragment.app.Fragment

abstract class BaseFragment(layoutRes: Int) : Fragment(layoutRes) {

    open fun onBackPressed() {}

    open val startAnimation = StartAnimation.ENTER

    enum class StartAnimation {
        SLIDE_UP, ENTER, NO_ANIM
    }

}