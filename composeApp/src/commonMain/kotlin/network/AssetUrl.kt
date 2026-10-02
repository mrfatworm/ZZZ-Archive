/*
 * Copyright 2026 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package network

import com.mrfatworm.zzzarchive.ZzzConfig

/** Full URL of a file under the asset repo's `Asset/` folder, e.g. `assetUrl("Banner/1.webp")`. */
fun assetUrl(path: String): String = "${ZzzConfig.ASSET_URL}/$path"
