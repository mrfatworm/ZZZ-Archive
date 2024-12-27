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
        "以太" to colorScheme.etherColor,
        " Ether " to colorScheme.etherColor,
        "火屬性" to colorScheme.fireColor,
        " Fire " to colorScheme.fireColor,
        "冰屬性" to colorScheme.iceColor,
        " Ice " to colorScheme.iceColor,
        "烈霜" to colorScheme.iceColor,
        " Forst " to colorScheme.iceColor,
        "電屬性" to colorScheme.electricColor,
        " Electric " to colorScheme.electricColor,
        "物理" to colorScheme.physicalColor,
        " Physical " to colorScheme.physicalColor
    )
    val percentagePattern = Regex("""\d+(\.\d+)?%(/\d+(\.\d+)?%)+""")

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

        percentagePattern.findAll(content).forEach { matchResult ->
            val start = matchResult.range.first
            val end = matchResult.range.last + 1
            addStyle(
                style = SpanStyle(color = colorScheme.primary),
                start = start,
                end = end
            )
        }
    }
    return annotatedString
}