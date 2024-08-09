/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: Apache-2.0
 */

fun selectAiGender(genderCode: Int): String {
    return when(genderCode) {
        0 -> "Female"
        1 -> "Male"
        2 -> "Android"
        else -> "Unknown"
    }
}