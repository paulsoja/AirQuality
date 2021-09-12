package com.paulsoia.airquality.presentation.utils

import com.github.terrakok.cicerone.*

fun Navigator.setLaunchScreen(vararg screen: Screen) {
    applyCommands(
        Array(screen.size + 1) { pos ->
            when (pos) {
                0 -> BackTo(null)
                1 -> Replace(screen[pos - 1])
                else -> Forward(screen[pos - 1])
            }
        }
    )
}