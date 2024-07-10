/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: Apache-2.0
 */

package home.data

import database.ZzzDatabase

class ZzzRepositoryImpl(private val ZzzDatabase: ZzzDatabase): ZzzRepository {
    override fun getImageUrl(): String {
        return "https://mihoyo.com/anbi/jpg"
    }
}