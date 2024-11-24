/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package utils

// Issue: Cannot restart on Desktop and iOS
expect class AppActionsUseCase {
    fun restart()
}