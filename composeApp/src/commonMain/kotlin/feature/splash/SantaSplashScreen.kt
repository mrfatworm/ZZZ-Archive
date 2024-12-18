/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.img_santa_lucy
import zzzarchive.composeapp.generated.resources.img_snow

@Composable
fun SantaSplashScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFF000223), Color(0xFF0C0934)))),
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(Res.drawable.img_snow),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Image(
            modifier = Modifier.align(Alignment.Center).widthIn(max = 480.dp)
                .fillMaxWidth(0.5f),
            painter = painterResource(Res.drawable.img_santa_lucy),
            contentDescription = null,
        )
    }
}