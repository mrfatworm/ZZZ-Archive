/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import feature.splash.model.SplashState
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.app_name
import zzzarchive.composeapp.generated.resources.splash_background
import zzzarchive.composeapp.generated.resources.splash_logo

@Composable
fun SplashScreen(startMainFlow: () -> Unit) {
    val viewModel: SplashViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var isDark by AppTheme.isDark
    isDark = uiState.isDark
    var uiScale by AppTheme.uiScale
    uiScale = uiState.uiScale
    var fontScale by AppTheme.fontScale
    fontScale = uiState.fontScale

    LaunchedEffect(true) {
        delay(1000)
        startMainFlow()
    }
    SplashScreenSingle(uiState)
}
val splashColor = Color(0xFF3C3C3C)

@Composable
private fun AppInfo(modifier: Modifier, appVersion: String) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(Res.drawable.splash_logo),
            contentDescription = null
        )
        Text(
            text = stringResource(Res.string.app_name),
            style = AppTheme.typography.headlineMedium,
            color = splashColor
        )
        Text(
            text = appVersion,
            style = AppTheme.typography.titleSmall,
            color = splashColor.copy(alpha = 0.8f)
        )
    }
}


@Composable
fun SplashScreenSingle(
    uiState: SplashState,
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
        AppInfo(
            modifier = Modifier.align(Alignment.Center), uiState.appVersion
        )
    }
}
