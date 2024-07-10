/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: Apache-2.0
 */

package home.domain

import androidx.lifecycle.ViewModel
import home.data.ZzzRepository

class ZzzViewModel(private val repository: ZzzRepository) : ViewModel() {

    fun getImageUrl(): String {
        return repository.getImageUrl()
    }
}