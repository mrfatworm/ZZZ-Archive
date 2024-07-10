/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: Apache-2.0
 */

package di

import home.data.ZzzRepository
import home.data.ZzzRepositoryImpl
import home.domain.ZzzViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { ZzzRepositoryImpl(get()) }.bind<ZzzRepository>()
    viewModelOf(::ZzzViewModel)

}