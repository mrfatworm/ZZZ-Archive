import androidx.compose.ui.window.ComposeUIViewController
import di.initKoin

fun MainViewController() = ComposeUIViewController(configure = {
    initKoin()
}) {
    ZzzArchiveApp()
}