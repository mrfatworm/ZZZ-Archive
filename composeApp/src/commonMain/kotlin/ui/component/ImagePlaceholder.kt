/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview


private val firstColorRow = listOf(
    Color(0XFFFFFFFF),
    Color(0XFFF7F21E),
    Color(0XFF61D0E4),
    Color(0XFF6ED340),
    Color(0XFFD351B1),
    Color(0XFFEC2326),
    Color(0XFF3E61C0)
)

private val secondColorRow = listOf(
    Color(0XFF3E61C0),
    Color(0XFF000000),
    Color(0XFFD351B1),
    Color(0XFF000000),
    Color(0XFF61D0E4),
    Color(0XFF000000),
    Color(0XFFFFFFFF)
)

private val thirdColorRow = listOf(
    Color(0XFF182343), Color(0XFFFFFFFF), Color(0XFF4D0D8E), Color(0XFF3E3E3E), Color(0XFF000000)
)

@Composable
fun ImageNotFound() {
    Column(Modifier.fillMaxSize()) {
        Row(Modifier.weight(0.7f)) {
            for (color in firstColorRow) {
                Spacer(
                    Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .background(color)
                )
            }
        }
        Row(Modifier.weight(0.1f)) {
            for (color in secondColorRow) {
                Spacer(
                    Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .background(color)
                )
            }
        }
        Row(Modifier.weight(0.2f)) {
            for (color in thirdColorRow) {
                Spacer(
                    Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .background(color)
                )
            }
        }
    }
}

@Preview
@Composable
fun ImageNotFoundPreview() {
    Box(modifier = Modifier.size(64.dp)) {
        ImageNotFound()
    }
}