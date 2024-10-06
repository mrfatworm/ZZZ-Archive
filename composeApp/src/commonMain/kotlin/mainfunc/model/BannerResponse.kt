package mainfunc.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BannerResponse(
    @SerialName("banner_id")
    val id: Int,
    val title: String,
    val url: String,
    val level: String,
    val ignorable: Boolean
) {
    fun getBannerLevel(): BannerLevel {
        return when (level) {
            "warning" -> BannerLevel.Warning
            "alert" -> BannerLevel.Alert
            else -> BannerLevel.Normal
        }
    }
}

enum class BannerLevel {
    Normal, Warning, Alert
}

val stubBannerResponse = BannerResponse(
    id = 1,
    title = "The app is still under development. If you encounter any issues, please feel free to report them to us.",
    url = "https://github.com/mrfatworm/ZZZ-Archive",
    level = "warning",
    ignorable = true
)