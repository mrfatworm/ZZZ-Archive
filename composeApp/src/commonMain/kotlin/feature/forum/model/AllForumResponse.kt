/*
 * Copyright 2025 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.forum.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllForumResponse(
    @SerialName("baha") val bahamut: List<BahamutForumListItemResponse>? = null,
    @SerialName("nga") val nga: List<NgaForumListItemResponse>? = null,
    @SerialName("ptt") val ptt: List<PttForumListItemResponse>? = null,
    @SerialName("x") val twitter: List<TwitterForumListItemResponse>? = null,
    @SerialName("reddit") val reddit: List<RedditForumListItem>? = null
)

@Serializable
data class BahamutForumListItemResponse(
    val link: String? = null,
    val imgSrc: String? = null,
    val title: String? = null,
    val gp: Int? = null
)

@Serializable
data class NgaForumListItemResponse(val link: String? = null, val title: String? = null, val replies: Int? = null)

@Serializable
data class PttForumListItemResponse(
    val link: String? = null,
    val title: String? = null,
    val popularity: Int? = null,
    val source: String? = null
)

@Serializable
data class TwitterForumListItemResponse(
    val title: String? = null,
    val link: String? = null,
    val image: String? = null,
    val author: String? = null,
    val authorProfile: String? = null,
    val hashtag: String? = null
)

@Serializable
data class RedditForumListItem(
    val title: String? = null,
    val link: String? = null,
    val image: String? = null,
    @SerialName("upvotes")
    val upVotes: Int? = null,
    val comments: Int? = null,
    val score: Int? = null
)

@Suppress("ktlint:standard:max-line-length")
val stubAllForumResponse =
    AllForumResponse(
        bahamut =
            listOf(
                BahamutForumListItemResponse(
                    link = "https://forum.gamer.com.tw/C.php?bsn=74860&snA=4527&tnum=1",
                    imgSrc = "https://truth.bahamut.com.tw/s01/202412/forum/7486/ce837662563ab6a49ab8bbd321005428.JPG?w=300&h=300&fit=o",
                    title = "【情報】邦布們的說明書｜格列佛探員",
                    gp = 35
                ),
                BahamutForumListItemResponse(
                    link = "https://forum.gamer.com.tw/C.php?bsn=74860&snA=4539&tnum=1",
                    imgSrc = "https://truth.bahamut.com.tw/s01/202412/forum/74860/b0a64872f780d94dfcd97f69a9aa61c8.JPG?w=300&h=300&fit=o",
                    title = "【繪圖】雅在進行對抗寒冷的修行",
                    gp = 67
                )
            ),
        nga =
            listOf(
                NgaForumListItemResponse(
                    link = "https://bbs.nga.cn/read.php?tid=42659556",
                    title = "[新闻] 《绝区零》1.4版本&#39;星流霆击&#39;前瞻信息汇总   ",
                    replies = 34
                ),
                NgaForumListItemResponse(
                    link = " https ://bbs.nga.cn/read.php?tid=42756365",
                    title = "[闲聊杂谈] b580这么强吗？不知道稳定性怎样",
                    replies = 22
                )
            ),
        ptt =
            listOf(
                PttForumListItemResponse(
                    link = "https://www.ptt.cc/bbs/miHoYo/M.1734358450.A.8EA.html",
                    title = "Re: [絕區] 星見雅的故事告訴了我們什麼？",
                    popularity = 4,
                    source = "miHoYo"
                ),
                PttForumListItemResponse(
                    link = "https://www.ptt.cc/bbs/miHoYo/M.1734354816.A.20F.html",
                    title = "Fw: [絕區] 星見雅的故事告訴了我們什麼？",
                    popularity = 1,
                    source = "miHoYo"
                )
            ),
        twitter =
            listOf(
                TwitterForumListItemResponse(
                    title = "Miyabi 🐱",
                    link = "https://x.com/zeltdraws/status/1868666139464876477",
                    image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRm0t_HPWKblrfxE-osoUIh9HHdKF3FE9BIXIniVDy5oDhouokc4QRKEJ4&s=0",
                    author = "Zeltdraws/Comms open 2/5 slots",
                    authorProfile = "https://x.com/zeltdraws",
                    hashtag = "#ゼンゼロ #젠존제 #zzzero"
                ),
                TwitterForumListItemResponse(
                    title = "Astra Yao Menú — Zenless Zone Zero",
                    link = "https://x.com/KdeKovaK/status/1868569844918174154",
                    image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRbsp8u8GcMLwXiyEfrSAQ-fuTyg9mCoUHc4Sv5OhAjn_bWXnQVMwD-wbs&s",
                    author = "K O V A K",
                    authorProfile = "https://x.com/KdeKovaK",
                    hashtag = "#ZenlessZoneZero #ZZZero #ゼンゼロ #ZZZ #zenless0704"
                )
            ),
        reddit =
            listOf(
                RedditForumListItem(
                    title = "Celebrating 200,000 members on Reddit. Thanks for your continuing support!",
                    link = "https://www.reddit.com/r/ZZZ_Official/comments/1hbigc4/celebrating_200000_members_on_reddit_thanks_for/",
                    image = "https://a.thumbs.redditmedia.com/GmexX159lYFgGX0GTjPOd6BVMAggn5ymvY-pIiJX_z0.jpg",
                    upVotes = 6172,
                    comments = 59,
                    score = 6172
                ),
                RedditForumListItem(
                    title = "ZZZ_Official Giveaway: Find Out Your Intimacy with Section 6 Characters",
                    link = "https://www.reddit.com/r/ZZZ_Official/comments/1h8f1z9/zzz_official_giveaway_find_out_your_intimacy_with/",
                    image = "https://b.thumbs.redditmedia.com/5fZSx25BV3_UZt-CcX4yNBhS1iP5-vonSAgexXg1v0A.jpg",
                    upVotes = 899,
                    comments = 1660,
                    score = 899
                )
            )
    )
