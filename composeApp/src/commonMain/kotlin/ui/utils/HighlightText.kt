/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import ui.theme.ColorScheme


fun highlightText(content: String, colorScheme: ColorScheme): AnnotatedString {
    val keywords = mapOf(
        "以太" to colorScheme.ether,
        "以太屬性" to colorScheme.ether,
        " Ether " to colorScheme.ether,
        "火屬性" to colorScheme.fire,
        " Fire " to colorScheme.fire,
        "冰屬性" to colorScheme.ice,
        " Ice " to colorScheme.ice,
        "烈霜" to colorScheme.ice,
        " Forst " to colorScheme.ice,
        "電屬性" to colorScheme.electric,
        " Electric " to colorScheme.electric,
        "物理" to colorScheme.physical,
        "物理屬性" to colorScheme.physical,
        " Physical " to colorScheme.physical
    )

    val annotatedString = buildAnnotatedString {
        append(content)

        for ((keyword, color) in keywords) {
            var startIndex = content.indexOf(keyword)
            while (startIndex >= 0) {
                val endIndex = startIndex + keyword.length
                addStyle(
                    style = SpanStyle(color = color, fontWeight = FontWeight.Bold),
                    start = startIndex,
                    end = endIndex
                )
                startIndex = content.indexOf(keyword, startIndex + keyword.length)
            }
        }
    }
    return annotatedString
}