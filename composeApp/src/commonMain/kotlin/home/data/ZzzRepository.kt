/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package home.data

import home.model.OfficialActivities
import utils.ZzzResult

interface ZzzRepository {
    suspend fun getActivities(): ZzzResult<OfficialActivities>
}