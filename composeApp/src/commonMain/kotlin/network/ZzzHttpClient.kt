/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package network

import home.model.OfficialActivities

interface ZzzHttpClient {
    suspend fun requestActivities(): OfficialActivities
}