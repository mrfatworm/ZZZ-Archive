/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package utils.share

/** What a successful [ImageShareHandler] call did, so the UI can word its confirmation. */
enum class ShareOutcome {
    /** The system share sheet took the image; it gives its own feedback. */
    Shared,

    /** Desktop has no share sheet, so the image went to the clipboard instead. */
    Copied,
    Saved,

    /** The user backed out of a file dialog; nothing to report. */
    Cancelled
}

/**
 * Hands a rendered PNG to the platform. Bound in each `platformModule` like [utils.AppActionsUseCase]:
 * Android and iOS open the system share sheet, desktop copies to the clipboard.
 */
interface ImageShareHandler {
    /** False where the share sheet already offers saving (iOS) or the OS version cannot write the gallery. */
    val canSave: Boolean

    suspend fun share(
        png: ByteArray,
        fileName: String
    ): Result<ShareOutcome>

    suspend fun save(
        png: ByteArray,
        fileName: String
    ): Result<ShareOutcome>
}
