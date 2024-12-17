/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * Modify from Coil3 sample
 * Ref: https://proandroiddev.com/coil-for-compose-multiplatform-5745ea76356f
 * License: MIT License
 */

package utils

import coil3.ImageLoader
import coil3.PlatformContext
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.request.CachePolicy
import coil3.request.crossfade
import coil3.util.DebugLogger
import okio.FileSystem

fun imageLoaderMemoryCache(
    context: PlatformContext,
    debug: Boolean = false
): ImageLoader {
    return ImageLoader.Builder(context).memoryCachePolicy(CachePolicy.ENABLED).memoryCache {
        MemoryCache.Builder()
            // Set the max size to 25% of the app's available memory.
            .maxSizePercent(context, percent = 0.25).strongReferencesEnabled(true).build()
    }
        // Show a short crossfade when loading images asynchronously.
        .crossfade(true)
        .apply {
            if (debug) {
                logger(DebugLogger())
            }
        }.build()
}

fun imageLoaderDiskCache(
    context: PlatformContext,
    debug: Boolean = false
): ImageLoader {
    return ImageLoader.Builder(context)
        .diskCachePolicy(CachePolicy.ENABLED)
        .networkCachePolicy(CachePolicy.ENABLED)
        .diskCache {
            newDiskCache()
        }
        // Show a short crossfade when loading images asynchronously.
        .crossfade(true)
        .apply {
            if (debug) {
                logger(DebugLogger())
            }
        }.build()
}

fun newDiskCache(): DiskCache {
    return DiskCache.Builder().directory(FileSystem.SYSTEM_TEMPORARY_DIRECTORY / "image_cache")
        .maxSizeBytes(512L * 1024 * 1024) // 512MB
        .build()
}
