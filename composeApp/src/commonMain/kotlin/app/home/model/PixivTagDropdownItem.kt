package app.home.model

data class PixivTagDropdownItem(
    val tagText: String,
    val tagOnPixiv: String
)

val pixivTagDropdownItems = listOf(
    PixivTagDropdownItem("100 +", "ゼンゼロ100users入り"),
    PixivTagDropdownItem("500 +", "ゼンゼロ500users入り"),
    PixivTagDropdownItem("1000 +", "ゼンゼロ1000users入り")
)

