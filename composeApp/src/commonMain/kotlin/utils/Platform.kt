/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package utils

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform