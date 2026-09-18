/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package utils.share

class FakeImageShareHandler(
    override val canSave: Boolean = true,
    private val shareOutcome: ShareOutcome = ShareOutcome.Shared,
    private val saveOutcome: ShareOutcome = ShareOutcome.Saved
) : ImageShareHandler {
    var lastSharedPng: ByteArray? = null
        private set
    var lastSavedPng: ByteArray? = null
        private set
    var lastFileName: String? = null
        private set

    /** Set to make the next call fail with this instead of succeeding. */
    var failure: Throwable? = null

    override suspend fun share(
        png: ByteArray,
        fileName: String
    ): Result<ShareOutcome> {
        lastSharedPng = png
        lastFileName = fileName
        return failure?.let { Result.failure(it) } ?: Result.success(shareOutcome)
    }

    override suspend fun save(
        png: ByteArray,
        fileName: String
    ): Result<ShareOutcome> {
        lastSavedPng = png
        lastFileName = fileName
        return failure?.let { Result.failure(it) } ?: Result.success(saveOutcome)
    }
}
