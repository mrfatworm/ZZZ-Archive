/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.agent.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import coil3.compose.AsyncImage
import com.mrfatworm.zzzarchive.ZzzConfig
import ui.components.buttons.ZzzIconButton
import ui.components.dialogs.GalleryFullScreenDialog
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.back
import zzzarchive.composeapp.generated.resources.ic_arrow_back

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentGalleryScreen(
    agentId: Int,
    onBackClick: () -> Unit
) {
    val path = ZzzConfig.ASSET_PATH
    val imageUrls = listOf(
        "https://raw.githubusercontent.com/$path/Agent/Portrait/$agentId.webp",
        "https://raw.githubusercontent.com/$path/Agent/Mindscape/Partial/$agentId.webp",
        "https://raw.githubusercontent.com/$path/Agent/Mindscape/Full/$agentId.webp",
        "https://raw.githubusercontent.com/$path/W-Engine/Match-Agent/$agentId.webp"
    )
    var selectedImageUrl by remember { mutableStateOf<String?>(null) }

    Scaffold(
        containerColor = AppTheme.colors.surface,
        topBar = {
            TopAppBar(
                title = {},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
                navigationIcon = {
                    if (AppTheme.adaptiveLayoutType == AdaptiveLayoutType.Compact) {
                        ZzzIconButton(
                            modifier = Modifier.padding(start = AppTheme.spacing.s400),
                            iconRes = Res.drawable.ic_arrow_back,
                            contentDescriptionRes = Res.string.back,
                            onClick = onBackClick
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(AppTheme.spacing.s400),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s400)
        ) {
            imageUrls.forEach { url ->
                GalleryImage(
                    url = url,
                    onClick = { selectedImageUrl = it }
                )
            }
        }
    }

    selectedImageUrl?.let { url ->
        GalleryFullScreenDialog(
            url = url,
            onDismiss = { selectedImageUrl = null }
        )
    }
}

@Composable
fun GalleryImage(
    url: String,
    onClick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .widthIn(max = AppTheme.size.s400)
            .fillMaxWidth()
            .clip(AppTheme.shape.r400)
            .background(AppTheme.colors.surfaceContainer)
            .border(
                width = AppTheme.size.border,
                color = AppTheme.colors.imageBorder,
                shape = AppTheme.shape.r400
            )
            .clickable { onClick(url) }
    ) {
        AsyncImage(
            model = url,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}
