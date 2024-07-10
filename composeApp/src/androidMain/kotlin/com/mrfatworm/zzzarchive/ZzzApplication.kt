/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: Apache-2.0
 */

package com.mrfatworm.zzzarchive

import android.app.Application
import di.initKoin
import org.koin.android.ext.koin.androidContext

class ZzzApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@ZzzApplication)
        }
    }
}