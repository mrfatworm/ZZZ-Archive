package feature.forum.compoenents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import feature.forum.model.AllForumState
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.components.cards.CardHeader
import ui.components.cards.ContentCard
import ui.components.chips.ZzzFilterChip
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.bahamut
import zzzarchive.composeapp.generated.resources.forum_popular
import zzzarchive.composeapp.generated.resources.ic_attribute_fire
import zzzarchive.composeapp.generated.resources.nga
import zzzarchive.composeapp.generated.resources.ptt
import zzzarchive.composeapp.generated.resources.reddit

val forumSite = listOf(
    Res.string.reddit,
    Res.string.bahamut,
    Res.string.ptt,
    Res.string.nga
)

@Composable
fun AllForumCard(uiState: AllForumState) {
    val pagerState = rememberPagerState(pageCount = { forumSite.size })
    val coroutineScope = rememberCoroutineScope()
    ContentCard(hasDefaultPadding = false) {
        CardHeader(title = stringResource(Res.string.forum_popular), startContent = {
            Icon(
                modifier = Modifier.size(AppTheme.size.iconLarge),
                imageVector = vectorResource(Res.drawable.ic_attribute_fire),
                contentDescription = null,
                tint = AppTheme.colors.onSurfaceVariant
            )
        })
        ForumIndicator(
            modifier = Modifier,
            pageCount = pagerState.pageCount,
            currentPage = pagerState.currentPage,
            onClick = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(it)
                }
            })
        HorizontalPager(
            modifier = Modifier.pointerHoverIcon(PointerIcon.Hand),
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { currentPager ->
            when (currentPager) {
                0 -> RedditList(uiState.reddit)
                1 -> BahamutList(uiState.bahamut)
                2 -> PttList(uiState.ptt)
                3 -> NgaList(uiState.nga)
            }
        }
    }
}


@Composable
private fun ForumIndicator(
    modifier: Modifier, pageCount: Int, currentPage: Int, onClick: (Int) -> Unit
) {
    Row(
        modifier.fillMaxWidth().padding(
            start = AppTheme.spacing.s400,
            end = AppTheme.spacing.s400,
            bottom = AppTheme.spacing.s300
        ), horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)
    ) {
        for (page in 0 until pageCount) {
            val isSelect = page == currentPage
            ZzzFilterChip(
                text = stringResource(forumSite[page]),
                selected = isSelect,
            ) {
                onClick(page)
            }
        }
    }
}

