/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package utils

import kotlin.system.exitProcess

class AppActionsUseCaseImpl : AppActionsUseCase {
    override fun restart() {
        exitProcess(0)
    }
}
