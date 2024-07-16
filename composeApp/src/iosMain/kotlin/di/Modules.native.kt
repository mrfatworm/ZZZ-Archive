/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: Apache-2.0
 */

package di

import database.ZzzDatabase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule = module {
    singleOf(::ZzzDatabase)
}