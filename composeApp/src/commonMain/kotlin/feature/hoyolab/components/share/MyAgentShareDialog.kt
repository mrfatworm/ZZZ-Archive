/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.hoyolab.components.share

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import feature.hoyolab.model.agent.MyAgentDetailState
import feature.hoyolab.model.agent.ShareMessage
import feature.hoyolab.presentation.MyAgentDetailAction
import feature.setting.components.SettingSwitchItem
import kotlin.math.roundToInt
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import ui.components.buttons.ZzzOutlineButton
import ui.components.buttons.ZzzPrimaryButton
import ui.components.dialogs.DialogTopBar
import ui.theme.AppTheme
import ui.theme.ZzzCaptureTheme
import ui.utils.horizontalSafePadding
import ui.utils.verticalSafePadding
import utils.share.encodeToPng
import utils.todayIsoDate
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.close
import zzzarchive.composeapp.generated.resources.dark_theme
import zzzarchive.composeapp.generated.resources.display_uid
import zzzarchive.composeapp.generated.resources.ic_close
import zzzarchive.composeapp.generated.resources.ic_share
import zzzarchive.composeapp.generated.resources.image_copied
import zzzarchive.composeapp.generated.resources.image_saved
import zzzarchive.composeapp.generated.resources.loading
import zzzarchive.composeapp.generated.resources.save_image
import zzzarchive.composeapp.generated.resources.share
import zzzarchive.composeapp.generated.resources.share_card
import zzzarchive.composeapp.generated.resources.share_failed
import zzzarchive.composeapp.generated.resources.show_drive_details

/** 540dp of card at this density is a 1080px-wide picture, whatever screen it was composed on. */
private const val CAPTURE_DENSITY = 2f

/** After this the card ships with whatever loaded; a stuck download must not block sharing forever. */
private const val IMAGE_TIMEOUT_MS = 5_000L

/**
 * Previews the share card at screen width and captures it at full size. The card is composed
 * once, under a fixed density, and its draw is recorded into a [rememberGraphicsLayer]; the
 * preview is that same node scaled down by [fitWidth], and the capture is the layer rendered as
 * it is -- so what the user sees is exactly what gets shared.
 */
@Composable
fun MyAgentShareDialog(
    uiState: MyAgentDetailState,
    onAction: (MyAgentDetailAction) -> Unit,
    onDismiss: () -> Unit
) {
    val tracker = rememberShareImageTracker()
    val captureLayer = rememberGraphicsLayer()
    val scope = rememberCoroutineScope()
    var isCapturing by remember { mutableStateOf(false) }
    var captureFailed by remember { mutableStateOf(false) }
    val date = remember { todayIsoDate() }

    // A share that finished after the dialog was last closed must not greet this opening.
    LaunchedEffect(Unit) { onAction(MyAgentDetailAction.DismissShareMessage) }
    // Restarts whenever new images start loading (a toggled option), and is idle when none are.
    val pendingImages = tracker.pendingCount
    LaunchedEffect(pendingImages) {
        if (pendingImages > 0) {
            delay(IMAGE_TIMEOUT_MS)
            tracker.settleAll()
        }
    }

    val imagesReady = tracker.isReady
    val canAct = imagesReady && !isCapturing

    fun capture(deliver: (ByteArray) -> Unit) {
        scope.launch {
            isCapturing = true
            captureFailed = false
            onAction(MyAgentDetailAction.DismissShareMessage)
            runCatching {
                val bitmap = captureLayer.toImageBitmap()
                withContext(Dispatchers.Default) { bitmap.encodeToPng() }
            }.fold(onSuccess = deliver, onFailure = { captureFailed = true })
            isCapturing = false
        }
    }

    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(AppTheme.colors.surface)
                    .padding(horizontalSafePadding())
                    .padding(verticalSafePadding()),
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)
        ) {
            DialogTopBar(title = stringResource(Res.string.share_card), onDismiss = onDismiss)
            Column(
                modifier = Modifier.weight(1f).fillMaxWidth().verticalScroll(rememberScrollState())
            ) {
                Box(modifier = Modifier.fillMaxWidth().clip(AppTheme.shape.r400).fitWidth()) {
                    CompositionLocalProvider(LocalDensity provides Density(CAPTURE_DENSITY)) {
                        ZzzCaptureTheme(isDark = uiState.shareOptions.isDarkTheme) {
                            MyAgentShareCard(
                                modifier =
                                    Modifier.drawWithContent {
                                        captureLayer.record { this@drawWithContent.drawContent() }
                                        drawLayer(captureLayer)
                                    },
                                uiState = uiState,
                                tracker = tracker,
                                date = date
                            )
                        }
                    }
                }
            }
            SettingSwitchItem(stringResource(Res.string.display_uid), uiState.showUid) {
                onAction(MyAgentDetailAction.SetShowUid(it))
            }
            SettingSwitchItem(stringResource(Res.string.show_drive_details), uiState.shareOptions.showDriveDetails) {
                onAction(MyAgentDetailAction.SetShareDriveDetails(it))
            }
            SettingSwitchItem(stringResource(Res.string.dark_theme), uiState.shareOptions.isDarkTheme) {
                onAction(MyAgentDetailAction.SetShareDarkTheme(it))
            }
            ShareFeedback(
                message = uiState.shareMessage,
                captureFailed = captureFailed,
                imagesReady = imagesReady
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)
            ) {
                ZzzPrimaryButton(
                    modifier = Modifier.weight(1f),
                    text = stringResource(Res.string.share),
                    iconRes = Res.drawable.ic_share,
                    enabled = canAct
                ) {
                    capture { onAction(MyAgentDetailAction.ShareCard(it)) }
                }
                if (uiState.canSaveImage) {
                    ZzzOutlineButton(
                        modifier = Modifier.weight(1f),
                        text = stringResource(Res.string.save_image),
                        enabled = canAct
                    ) {
                        capture { onAction(MyAgentDetailAction.SaveCard(it)) }
                    }
                }
            }
        }
    }
}

/** Inline rather than a snackbar: the dialog covers the app's snackbar host. */
@Composable
private fun ShareFeedback(
    message: ShareMessage?,
    captureFailed: Boolean,
    imagesReady: Boolean
) {
    val failed = captureFailed || message == ShareMessage.Failed
    val textRes: StringResource? =
        when {
            !imagesReady -> Res.string.loading
            failed -> Res.string.share_failed
            message == ShareMessage.Copied -> Res.string.image_copied
            message == ShareMessage.Saved -> Res.string.image_saved
            else -> null
        }
    if (textRes != null) {
        Text(
            modifier = Modifier.fillMaxWidth().padding(horizontal = AppTheme.spacing.s400),
            text = stringResource(textRes),
            color = if (failed) AppTheme.colors.alert else AppTheme.colors.onSurfaceVariant,
            style = AppTheme.typography.labelMedium
        )
    }
}

/**
 * Lays the child out at its own size, then shows it shrunk to the available width -- never
 * enlarged, so a wide desktop window sees the card at its natural size. The scale is a layer
 * transform, so the child's own draw -- and the capture recorded from it -- stays full size.
 */
private fun Modifier.fitWidth(): Modifier = layout { measurable, constraints ->
    val placeable = measurable.measure(Constraints())
    val scale =
        if (constraints.hasBoundedWidth && placeable.width > 0) {
            (constraints.maxWidth.toFloat() / placeable.width).coerceAtMost(1f)
        } else {
            1f
        }
    val width = (placeable.width * scale).roundToInt()
    val height = (placeable.height * scale).roundToInt()
    layout(width, height) {
        placeable.placeWithLayer(0, 0) {
            scaleX = scale
            scaleY = scale
            transformOrigin = TransformOrigin(0f, 0f)
        }
    }
}
