/*
 *  Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 *  License: CC BY-SA 4.0
 */

package ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import utils.ZzzArchiveRarity

val itemSize = 80.dp
val largeItemSize = 120.dp

@Composable
fun SimpleItem(
    modifier: Modifier = Modifier,
    isLargeSize: Boolean = false,
    rarity: ZzzArchiveRarity = ZzzArchiveRarity.Four,
    name: String,
    imgUrl: String? = null,
    onClick: () -> Unit = {}
) {
    Column(modifier = modifier.clickable(onClick = onClick), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Surface(
            modifier = Modifier.size(if (isLargeSize) largeItemSize else itemSize),
            shape = RoundedCornerShape(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = rarity.color, shape = RoundedCornerShape(8.dp)
                    )
            ) {
                imgUrl?.let {
                    AsyncImage(
                        modifier = Modifier.fillMaxSize(),
                        model = imgUrl,
                        contentDescription = name
                    )
                }
            }
        }

        Text(
            modifier = Modifier.width(if (isLargeSize) largeItemSize else itemSize),
            text = name,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 2)
    }
}
