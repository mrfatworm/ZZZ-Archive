/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.hoyolab.components.share

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.github.panpf.sketch.AsyncImage
import com.github.panpf.sketch.AsyncImageState
import com.github.panpf.sketch.rememberAsyncImageState
import com.github.panpf.sketch.request.ImageRequest
import com.github.panpf.sketch.request.LoadState
import kotlinx.coroutines.flow.first

/**
 * Knows whether every image on the share card has finished loading, so the capture is not taken
 * while Sketch is still replacing a placeholder.
 */
@Stable
class ShareImageTracker {
    private val settled = mutableStateMapOf<String, Boolean>()

    /** True once every tracked image has finished, either way; a card with no images is ready at once. */
    val isReady: Boolean
        get() = settled.values.all { it }

    val pendingCount: Int
        get() = settled.values.count { !it }

    fun track(key: String) {
        if (key !in settled) settled[key] = false
    }

    fun settle(key: String) {
        settled[key] = true
    }

    /** Gives up on whatever is still loading, so a stuck download cannot block sharing forever. */
    fun settleAll() {
        for (key in settled.keys.toList()) settled[key] = true
    }
}

@Composable
fun rememberShareImageTracker(): ShareImageTracker = remember { ShareImageTracker() }

/** An [AsyncImage] that reports to [tracker] once its request has ended, successfully or not. */
@Composable
fun TrackedAsyncImage(
    uri: String,
    tracker: ShareImageTracker,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit
) {
    AsyncImage(
        uri = uri,
        contentDescription = null,
        modifier = modifier,
        state = rememberTrackedImageState(key = uri, tracker = tracker),
        contentScale = contentScale
    )
}

/** As [TrackedAsyncImage], for a request that carries its own cache or size rules. */
@Composable
fun TrackedAsyncImage(
    request: ImageRequest,
    tracker: ShareImageTracker,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit
) {
    AsyncImage(
        request = request,
        contentDescription = null,
        modifier = modifier,
        state = rememberTrackedImageState(key = request.uri.toString(), tracker = tracker),
        contentScale = contentScale
    )
}

@Composable
private fun rememberTrackedImageState(
    key: String,
    tracker: ShareImageTracker
): AsyncImageState {
    val state = rememberAsyncImageState()
    LaunchedEffect(key, state) {
        tracker.track(key)
        // An error settles too: a broken URL must not hold the share button hostage.
        snapshotFlow { state.loadState }.first { it is LoadState.Success || it is LoadState.Error }
        tracker.settle(key)
    }
    return state
}
