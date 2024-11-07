/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.mrfatworm.zzzarchive.ZzzConfig
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import ui.theme.AppTheme
import ui.utils.ContentType
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.app_name
import zzzarchive.composeapp.generated.resources.splash_background
import zzzarchive.composeapp.generated.resources.splash_logo

@Composable
fun SplashScreen(
    contentType: ContentType, startMainFlow: () -> Unit
) {
    val viewModel: SplashViewModel = koinViewModel()
    val isDark by viewModel.isDark.collectAsState()
    var isDarkComposeState by AppTheme.isDark
    isDarkComposeState = isDark

    LaunchedEffect(true) {
        delay(1000)
        startMainFlow()
    }
    if (contentType == ContentType.Single) {
        SplashScreenSingle()
    } else {
        SplashScreenDual()
    }
}
val splashColor = Color(0xFFB78B07)

@Composable
private fun AppInfo(modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Image(
            modifier = Modifier.size(240.dp)
                .border(8.dp, splashColor.copy(alpha = 0.3f), shape = RoundedCornerShape(32.dp)),
            painter = painterResource(Res.drawable.splash_logo),
            contentDescription = null
        )
        Text(
            text = stringResource(Res.string.app_name),
            style = AppTheme.typography.headlineMedium,
            color = splashColor
        )
        Text(
            text = ZzzConfig.VERSION,
            style = AppTheme.typography.headlineMedium,
            color = splashColor.copy(alpha = 0.8f)
        )
    }
}


@Composable
fun SplashScreenSingle(
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(Res.drawable.splash_background),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        AppInfo(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun SplashScreenDual() {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(Res.drawable.splash_background),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        AppInfo(modifier = Modifier.align(Alignment.Center))
    }
}
