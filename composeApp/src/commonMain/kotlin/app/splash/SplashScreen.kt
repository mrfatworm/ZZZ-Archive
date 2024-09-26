/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mrfatworm.zzzarchive.ZzzConfig
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.theme.AppTheme
import ui.utils.ContentType
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.app_name
import zzzarchive.composeapp.generated.resources.ic_bangboo

@Composable
fun SplashScreen(
    contentType: ContentType, startMainFlow: () -> Unit
) {
    LaunchedEffect(true) {
        delay(1000)
        startMainFlow()
    }
    if (contentType == ContentType.SINGLE) {
        SplashScreenSingle()
    } else {
        SplashScreenDual()
    }
}


@Composable
fun SplashScreenSingle(
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            16.dp, alignment = Alignment.CenterVertically
        )
    ) {
        Icon(
            modifier = Modifier.size(64.dp),
            imageVector = vectorResource(Res.drawable.ic_bangboo),
            contentDescription = null,
            tint = AppTheme.colors.onSurface
        )
        Text(
            text = stringResource(Res.string.app_name) + " " + ZzzConfig.variant,
            style = AppTheme.typography.headlineMedium,
            color = AppTheme.colors.onSurface
        )
    }
}

@Composable
fun SplashScreenDual() {
    Row(
        modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.spacedBy(
            16.dp, alignment = Alignment.CenterHorizontally
        ), verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(64.dp),
            imageVector = vectorResource(Res.drawable.ic_bangboo),
            contentDescription = null,
            tint = AppTheme.colors.onSurface
        )
        Text(
            text = stringResource(Res.string.app_name) + " " + ZzzConfig.variant,
            style = AppTheme.typography.headlineMedium,
            color = AppTheme.colors.onSurface
        )
    }
}
