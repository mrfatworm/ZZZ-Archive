/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package di

import home.data.ZzzRepository
import home.data.ZzzRepositoryImpl
import home.domain.ZzzViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { ZzzRepositoryImpl(get(), get()) }.bind<ZzzRepository>()
    viewModelOf(::ZzzViewModel)

}