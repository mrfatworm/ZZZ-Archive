/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package utils

sealed class ZzzResult<out R> {
    data object Loading : ZzzResult<Nothing>()
    data class Success<out T>(val data: T) : ZzzResult<T>()
    data class Error(val message: String) : ZzzResult<Nothing>()
}