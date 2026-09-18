/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package ui.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight

/**
 * Renders the markup HoYoLab embeds in agent skill, mindscape and awakening descriptions.
 *
 * Only five shapes occur in the live responses: `<color=#RRGGBB>…</color>`,
 * `<span style="color: #fff">…</span>`, a stray unmatched `</Term>` that always trails such a span,
 * and `<IconMap:Icon_*>` placeholders for the in-game button glyphs, which have no asset here.
 * Colour tags nest, so they are tracked as a stack.
 *
 * Line breaks arrive double escaped: the payload carries `\\n`, so the decoded string holds the two
 * characters `\` and `n` rather than a newline, and has to be unescaped here. Every line break in
 * the live data is like this -- there is not a single real newline character in any description.
 *
 * The game emphasises keywords in pure white, which would be invisible on a light surface, so white
 * is remapped to [emphasisColor] and bolded. Every other colour is the game's own attribute or buff
 * colour and is kept as it is.
 */
fun hoYoLabRichText(
    content: String,
    emphasisColor: Color
): AnnotatedString {
    val cleaned = content
        .replace(ESCAPED_NEWLINE, "\n")
        .replace(ICON_PLACEHOLDER, "")
        .replace("</Term>", "")
        .replace(REPEATED_SPACES, " ")

    return buildAnnotatedString {
        var index = 0
        var openStyles = 0
        while (index < cleaned.length) {
            if (cleaned[index] != '<') {
                val nextTag = cleaned.indexOf('<', index).takeIf { it >= 0 } ?: cleaned.length
                append(cleaned.substring(index, nextTag))
                index = nextTag
                continue
            }
            val tagEnd = cleaned.indexOf('>', index)
            if (tagEnd < 0) {
                append(cleaned.substring(index))
                break
            }
            val tag = cleaned.substring(index + 1, tagEnd)
            when {
                tag.startsWith("color=") -> {
                    pushStyle(spanStyleOf(parseHexColor(tag.removePrefix("color=")), emphasisColor))
                    openStyles++
                }

                tag.startsWith("span") -> {
                    pushStyle(
                        spanStyleOf(
                            SPAN_COLOR.find(tag)?.groupValues?.get(1)?.let { parseHexColor(it) },
                            emphasisColor
                        )
                    )
                    openStyles++
                }

                tag == "/color" || tag == "/span" -> {
                    if (openStyles > 0) {
                        pop()
                        openStyles--
                    }
                }

                // Anything else is markup we have no rendering for; drop the tag, keep the text.
                else -> Unit
            }
            index = tagEnd + 1
        }
        repeat(openStyles) { pop() }
    }
}

private const val ESCAPED_NEWLINE = "\\n"

private val ICON_PLACEHOLDER = Regex("<IconMap:[^<>]*>")
private val REPEATED_SPACES = Regex("[ 　]{2,}")
private val SPAN_COLOR = Regex("color:\\s*(#[0-9a-fA-F]{3,8})")

private fun spanStyleOf(
    color: Color?,
    emphasisColor: Color
): SpanStyle = when {
    color == null -> SpanStyle(fontWeight = FontWeight.Bold)

    // Pure white is the game's keyword emphasis, not a colour choice worth honouring on a light theme.
    color == Color.White -> SpanStyle(color = emphasisColor, fontWeight = FontWeight.Bold)

    else -> SpanStyle(color = color, fontWeight = FontWeight.Bold)
}

/** `#rgb` or `#rrggbb`, as HoYoLab writes colours in rich text and painting metadata; null otherwise. */
fun parseHexColor(raw: String): Color? {
    // The live data only ever uses #RGB or #RRGGBB.
    val hex = raw.trim().removePrefix("#")
    val rgb = when (hex.length) {
        3 -> hex.map { "$it$it" }.joinToString("")
        6 -> hex
        else -> return null
    }
    val value = rgb.toLongOrNull(16) ?: return null
    return Color(0xFF000000L.or(value).toInt())
}
