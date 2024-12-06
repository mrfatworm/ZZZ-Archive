/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.banner.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.banner.data.BannerResponse
import ui.components.Banner
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.view_detail

@Composable
fun AnnouncementBanner(
    banner: BannerResponse?,
    onActionClicked: () -> Unit,
    onClosed: (Int) -> Unit
) {
    AnimatedVisibility(visible = banner != null) {
        banner?.let {
            Banner(
                modifier = Modifier.widthIn(max = AppTheme.size.maxContainerWidth),
                title = banner.title,
                bannerLevel = banner.getBannerLevel(),
                closable = banner.ignorable,
                actionTextRes = Res.string.view_detail,
                onActionClicked = onActionClicked,
                onClosed = {
                    onClosed(banner.id)
                })
        }
    }
}