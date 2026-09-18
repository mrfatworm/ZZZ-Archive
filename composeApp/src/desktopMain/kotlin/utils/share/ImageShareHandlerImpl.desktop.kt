/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package utils.share

import java.awt.FileDialog
import java.awt.Frame
import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.Transferable
import java.awt.datatransfer.UnsupportedFlavorException
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * The desktop JVM has no share sheet, so sharing means the clipboard. Saving goes through the AWT
 * file dialog rather than a fixed folder: it is the one write path the macOS App Store sandbox
 * allows outside the app container.
 */
class ImageShareHandlerImpl : ImageShareHandler {
    override val canSave: Boolean = true

    override suspend fun share(
        png: ByteArray,
        fileName: String
    ): Result<ShareOutcome> = runCatching {
        val image =
            withContext(Dispatchers.IO) { ImageIO.read(png.inputStream()) } ?: error("Cannot decode the PNG")
        withContext(Dispatchers.Main) {
            Toolkit.getDefaultToolkit().systemClipboard.setContents(ImageTransferable(image), null)
        }
        ShareOutcome.Copied
    }

    override suspend fun save(
        png: ByteArray,
        fileName: String
    ): Result<ShareOutcome> = runCatching {
        // Modal, but AWT pumps events while it is up; it has to be shown from the UI thread.
        val target =
            withContext(Dispatchers.Main) {
                FileDialog(null as Frame?, "", FileDialog.SAVE).run {
                    file = fileName
                    isVisible = true
                    val chosenDirectory = directory
                    val chosenName = file
                    if (chosenDirectory == null || chosenName == null) null else File(chosenDirectory, chosenName)
                }
            } ?: return@runCatching ShareOutcome.Cancelled
        withContext(Dispatchers.IO) { target.writeBytes(png) }
        ShareOutcome.Saved
    }
}

private class ImageTransferable(private val image: BufferedImage) : Transferable {
    override fun getTransferDataFlavors(): Array<DataFlavor> = arrayOf(DataFlavor.imageFlavor)

    override fun isDataFlavorSupported(flavor: DataFlavor): Boolean = flavor == DataFlavor.imageFlavor

    override fun getTransferData(flavor: DataFlavor): Any =
        if (isDataFlavorSupported(flavor)) image else throw UnsupportedFlavorException(flavor)
}
