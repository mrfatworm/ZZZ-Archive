/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package utils

class FakeAppActionsUseCase : AppActionsUseCase {
    var restartCount = 0
        private set

    override fun restart() {
        restartCount++
    }
}
