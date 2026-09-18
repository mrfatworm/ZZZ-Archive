/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package utils

import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/** Today in the device's zone as ISO `yyyy-MM-dd`, which also sorts in a folder listing. */
@OptIn(ExperimentalTime::class)
fun todayIsoDate(): String = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.toString()
