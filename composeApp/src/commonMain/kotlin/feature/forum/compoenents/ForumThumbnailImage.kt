package feature.forum.compoenents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.github.panpf.sketch.AsyncImage
import com.github.panpf.sketch.SubcomposeAsyncImage
import ui.components.ImageNotFound
import ui.theme.AppTheme

@Composable
fun ForumThumbnailImage(imgUrl: String) {
    Box(
        modifier =
            Modifier
                .height(AppTheme.size.s72)
                .aspectRatio(4 / 3f)
                .clip(AppTheme.shape.r300)
    ) {
        AsyncImage(
            modifier = Modifier.matchParentSize().blur(8.dp),
            uri = imgUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            filterQuality = FilterQuality.None
        )
        SubcomposeAsyncImage(
            modifier = Modifier.matchParentSize(),
            uri = imgUrl,
            contentDescription = null,
            filterQuality = FilterQuality.None,
            error = {
                ImageNotFound()
            }
        )
    }
}
