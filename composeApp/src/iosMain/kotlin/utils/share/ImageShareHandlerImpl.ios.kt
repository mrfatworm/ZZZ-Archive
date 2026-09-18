/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package utils.share

import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.useContents
import kotlinx.cinterop.usePinned
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import platform.CoreGraphics.CGRectMake
import platform.Foundation.NSData
import platform.Foundation.create
import platform.UIKit.UIActivityViewController
import platform.UIKit.UIApplication
import platform.UIKit.UIImage
import platform.UIKit.UIViewController
import platform.UIKit.popoverPresentationController

/** The activity sheet already carries "Save Image", so there is no separate save path on iOS. */
class ImageShareHandlerImpl : ImageShareHandler {
    override val canSave: Boolean = false

    @OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
    override suspend fun share(
        png: ByteArray,
        fileName: String
    ): Result<ShareOutcome> = runCatching {
        val data = png.usePinned { pinned -> NSData.create(bytes = pinned.addressOf(0), length = png.size.toULong()) }
        val image = checkNotNull(UIImage.imageWithData(data)) { "UIKit could not decode the PNG" }
        withContext(Dispatchers.Main) {
            val host = checkNotNull(topViewController()) { "No view controller to present from" }
            val sheet = UIActivityViewController(activityItems = listOf(image), applicationActivities = null)
            // On iPad the sheet is a popover, which has to be anchored to something.
            sheet.popoverPresentationController?.let { popover ->
                popover.sourceView = host.view
                host.view.bounds.useContents {
                    popover.sourceRect = CGRectMake(size.width / 2, size.height / 2, 0.0, 0.0)
                }
            }
            host.presentViewController(sheet, animated = true, completion = null)
        }
        ShareOutcome.Shared
    }

    override suspend fun save(
        png: ByteArray,
        fileName: String
    ): Result<ShareOutcome> = Result.failure(UnsupportedOperationException("Use the share sheet's Save Image on iOS"))

    private fun topViewController(): UIViewController? {
        var controller = UIApplication.sharedApplication.keyWindow?.rootViewController
        while (controller?.presentedViewController != null) {
            controller = controller.presentedViewController
        }
        return controller
    }
}
