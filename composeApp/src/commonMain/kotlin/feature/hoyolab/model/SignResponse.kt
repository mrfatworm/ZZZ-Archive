/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignResponse(
    @SerialName("retcode")
    val retCode: Int,
    val message: String
)

val stubSignResponse = SignResponse(retCode = 0, message = "OK")