/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package utils

// Replace ZzzResult. State: Processing
sealed class UiResult<out R> {
    data object Loading : UiResult<Nothing>()
    data class Success<out T>(val data: T) : UiResult<T>()
    data class Error(val message: String) : UiResult<Nothing>()
}