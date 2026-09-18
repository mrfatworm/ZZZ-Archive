/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package utils.share

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import java.io.File
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImageShareHandlerImpl(private val context: Context) : ImageShareHandler {
    // MediaStore.RELATIVE_PATH is what lets the app write to Pictures/ without a storage permission.
    override val canSave: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

    override suspend fun share(
        png: ByteArray,
        fileName: String
    ): Result<ShareOutcome> = runCatching {
        val file =
            withContext(Dispatchers.IO) {
                File(context.cacheDir, SHARE_DIR).apply { mkdirs() }.resolve(fileName).apply { writeBytes(png) }
            }
        val uri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
        val send =
            Intent(Intent.ACTION_SEND).apply {
                type = MIME_PNG
                putExtra(Intent.EXTRA_STREAM, uri)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
        // Started from the application context, so the chooser needs a task of its own.
        context.startActivity(Intent.createChooser(send, null).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        ShareOutcome.Shared
    }

    override suspend fun save(
        png: ByteArray,
        fileName: String
    ): Result<ShareOutcome> = runCatching {
        check(canSave) { "Saving to the gallery needs Android 10" }
        withContext(Dispatchers.IO) { insertIntoGallery(png, fileName) }
        ShareOutcome.Saved
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun insertIntoGallery(
        png: ByteArray,
        fileName: String
    ) {
        val resolver = context.contentResolver
        val values =
            ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
                put(MediaStore.Images.Media.MIME_TYPE, MIME_PNG)
                put(MediaStore.Images.Media.RELATIVE_PATH, "${Environment.DIRECTORY_PICTURES}/$ALBUM")
                put(MediaStore.Images.Media.IS_PENDING, 1)
            }
        val collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        val uri = checkNotNull(resolver.insert(collection, values)) { "MediaStore refused the image" }
        resolver.openOutputStream(uri)?.use { it.write(png) } ?: error("Cannot open $uri for writing")
        values.clear()
        values.put(MediaStore.Images.Media.IS_PENDING, 0)
        resolver.update(uri, values, null, null)
    }

    private companion object {
        const val SHARE_DIR = "share"
        const val ALBUM = "ZZZ Archive"
        const val MIME_PNG = "image/png"
    }
}
