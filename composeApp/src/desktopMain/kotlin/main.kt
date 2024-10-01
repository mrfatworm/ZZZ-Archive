import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            state = rememberWindowState(width = 1280.dp, height = 960.dp),
            title = "ZZZ Archive",
        ) {
            ZzzArchiveApp()
        }
    }
}