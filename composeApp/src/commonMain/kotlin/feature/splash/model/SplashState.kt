/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.splash.model

data class SplashState(
    val isDark: Boolean = true,
    val uiScale: Float = 1f,
    val fontScale: Float = 1f,
    val appVersion: String = "",
)
