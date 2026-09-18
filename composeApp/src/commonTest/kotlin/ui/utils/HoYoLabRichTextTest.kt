/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package ui.utils

import androidx.compose.ui.graphics.Color
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class HoYoLabRichTextTest {
    private val emphasis = Color(0xFF123456)

    private fun render(content: String) = hoYoLabRichText(content, emphasis)

    @Test
    fun `Colour tags are dropped from the text and kept as a span`() {
        val result = render("造成<color=#2BAD00>150%</color>傷害")
        assertEquals("造成150%傷害", result.text)
        val span = result.spanStyles.single()
        assertEquals(Color(0xFF2BAD00), span.item.color)
        assertEquals("150%", result.text.substring(span.start, span.end))
    }

    @Test
    fun `White keywords are remapped to the emphasis colour`() {
        assertEquals(emphasis, render("<color=#FFFFFF>[普通攻擊]</color>").spanStyles.single().item.color)
        assertEquals(emphasis, render("""<span style="color: #fff">[虛曜]</span>""").spanStyles.single().item.color)
    }

    @Test
    fun `Button glyph placeholders are removed and the gap they leave is collapsed`() {
        assertEquals("點按 發動：", render("點按 <IconMap:Icon_Normal> 發動：").text)
    }

    @Test
    fun `The unmatched Term tag HoYoLab trails spans with is removed`() {
        val result = render("""回復<span style="color: #fff">[浮暉]</span></Term>。""")
        assertEquals("回復[浮暉]。", result.text)
        assertEquals(1, result.spanStyles.size)
    }

    @Test
    fun `Nested colour tags both apply and unwind in order`() {
        val result = render("""<color=#FFFFFF><span style="color: #fff">[驚嚇]</span></Term></color>達到6層""")
        assertEquals("[驚嚇]達到6層", result.text)
        assertEquals(2, result.spanStyles.size)
        assertTrue(result.spanStyles.all { it.item.color == emphasis })
    }

    @Test
    fun `Malformed markup degrades to plain text instead of throwing`() {
        assertEquals("未關閉的標籤", render("<color=#FFFFFF>未關閉的標籤").text)
        assertEquals("多餘的結束標籤", render("多餘的結束標籤</color></color>").text)
        // A '<' with no '>' after it is kept verbatim rather than swallowing the rest of the text.
        assertEquals("未完成的標籤<color=#FFF", render("未完成的標籤<color=#FFF").text)
        assertEquals("略過未知標籤", render("略過<b>未知</b>標籤").text)
    }

    @Test
    fun `Double escaped line breaks become real newlines`() {
        // HoYoLab sends \\n on the wire, so the decoded string holds a backslash and an n, not a
        // newline. Rendering it verbatim printed a literal \n in the dialogs.
        assertEquals("第一行\n第二行", render("第一行\\n第二行").text)
        assertEquals(0, render("第一行\\n第二行").text.count { it == '\\' })
    }

    @Test
    fun `A live description keeps every character outside the markup`() {
        val live =
            "點按 <IconMap:Icon_Normal> 發動：\\n向前方進行至多四段攻擊，造成<color=#FFA9DD>流明屬性傷害</color>。\\n" +
                "若普攻命中目標，招式結束後可為自身回復<span style=\"color: #fff\">[浮暉]</span></Term>。"
        val result = render(live)
        assertEquals(
            "點按 發動：\n向前方進行至多四段攻擊，造成流明屬性傷害。\n若普攻命中目標，招式結束後可為自身回復[浮暉]。",
            result.text
        )
        assertEquals(listOf(Color(0xFFFFA9DD), emphasis), result.spanStyles.map { it.item.color })
    }
}
