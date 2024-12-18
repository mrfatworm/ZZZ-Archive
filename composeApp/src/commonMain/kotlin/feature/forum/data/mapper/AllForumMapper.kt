package feature.forum.data.mapper

import feature.forum.model.AllForumResponse
import feature.forum.model.AllForumState
import feature.forum.model.BahamutForumListItem
import feature.forum.model.BahamutForumListState
import feature.forum.model.NgaForumListItem
import feature.forum.model.NgaForumListState
import feature.forum.model.PttForumListItem
import feature.forum.model.PttForumListState
import feature.forum.model.RedditForumListItem
import feature.forum.model.RedditForumListState
import feature.forum.model.TwitterForumListItem
import feature.forum.model.TwitterForumListState

fun AllForumResponse.toAllForumState(): AllForumState {
    return AllForumState(
        reddit = reddit.map { it.toRedditForumListState() },
        bahamut = bahamut.map { it.toBahamutForumListState() },
        ptt = ptt.map { it.toPttForumListState() },
        twitter = twitter.map { it.toTwitterForumListState() },
        nga = nga.map { it.toNgaForumListState() },
    )
}

fun RedditForumListItem.toRedditForumListState(): RedditForumListState {
    return RedditForumListState(
        title = title,
        link = link,
        imgUrl = image,
        upVotes = upVotes.toString(),
        comments = comments.toString(),
        score = score.toString()
    )
}

fun BahamutForumListItem.toBahamutForumListState(): BahamutForumListState {
    return BahamutForumListState(
        title = splitBahamutText(title).second,
        category = splitBahamutText(title).first,
        link = link,
        imgUrl = imgSrc,
        gp = gp.toString()
    )
}

fun PttForumListItem.toPttForumListState(): PttForumListState {
    return PttForumListState(
        link = link,
        title = title,
        popularity = if (popularity >= 100) "爆" else popularity.toString(),
        source = source
    )
}

fun TwitterForumListItem.toTwitterForumListState(): TwitterForumListState {
    return TwitterForumListState(
        title = title,
        link = link,
        imgUrl = image,
        author = author,
        authorUrl = authorProfile,
        hashtag = hashtag
    )
}

fun NgaForumListItem.toNgaForumListState(): NgaForumListState {
    return NgaForumListState(
        title = title,
        link = link,
        replies = replies.toString()
    )
}


fun splitBahamutText(input: String): Pair<String, String> {
    val regex = Regex("【(.*?)】(.*)")
    val matchResult = regex.find(input)
    return if (matchResult != null) {
        val (category, content) = matchResult.destructured
        category to content.trim()
    } else {
        "" to input
    }
}