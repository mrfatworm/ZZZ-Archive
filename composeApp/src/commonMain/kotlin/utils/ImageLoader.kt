/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package utils

import com.github.panpf.sketch.PlatformContext
import com.github.panpf.sketch.Sketch
import com.github.panpf.sketch.cache.DiskCache
import com.github.panpf.sketch.cache.MemoryCache
import com.github.panpf.sketch.decode.supportAnimatedWebp
import com.github.panpf.sketch.request.ImageOptions
import com.github.panpf.sketch.util.Logger

fun newSketch(
    context: PlatformContext,
    debug: Boolean = false
): Sketch = Sketch(context) {
    // Set the memory cache to 25% of the app's available memory.
    memoryCache {
        MemoryCache.Builder(context)
            .maxSizePercent(0.25)
            .build()
    }
    // Network disk cache in the platform's default cache directory.
    downloadCacheOptions(
        DiskCache.Options(maxSize = 512L * 1024 * 1024) // 512MB
    )
    // Decode animated WebP on every platform (Android ImageDecoder / Skia).
    addComponents {
        supportAnimatedWebp()
    }
    // Show a short crossfade when loading images asynchronously.
    globalImageOptions(
        ImageOptions {
            crossfade(true)
        }
    )
    if (debug) {
        logger(level = Logger.Level.Debug)
    }
}
