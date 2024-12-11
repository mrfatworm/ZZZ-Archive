/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.model

enum class ServersList(val localName: String, val region: String) {
    ASIA("Asia", "prod_gf_jp"),
    AMERICA("America", "prod_gf_us"),
    EUROPE("Europe", "prod_gf_eu"),
    TW_HK_MO("TW,HK,MO", "prod_gf_sg")
}
