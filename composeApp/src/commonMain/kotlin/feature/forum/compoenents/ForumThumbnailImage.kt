package feature.forum.compoenents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage
import ui.components.ImageNotFound
import ui.theme.AppTheme

@Composable
fun ForumThumbnailImage(imgUrl: String) {
    Box(
        modifier = Modifier.height(AppTheme.size.s72).aspectRatio(1.33f)
            .clip(AppTheme.shape.r300)
    ) {
        if (imgUrl == "") {
            Spacer(Modifier.matchParentSize().background(AppTheme.colors.surface))
        } else {
            AsyncImage(
                modifier = Modifier.matchParentSize().blur(8.dp),
                model = imgUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                filterQuality = FilterQuality.None
            )
            SubcomposeAsyncImage(
                modifier = Modifier.matchParentSize(),
                model = imgUrl,
                contentDescription = null,
                filterQuality = FilterQuality.None,
                error = {
                    ImageNotFound()
                })
        }
    }
}