/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

fun selectAiGenderDesktop(genderCode: Int): String {
    return when(genderCode) {
        0 -> "Female"
        1 -> "Male"
        2 -> "Android"
        else -> "Unknown"
    }
}
