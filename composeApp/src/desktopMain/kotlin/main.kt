import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import di.initKoin
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.app_name
import zzzarchive.composeapp.generated.resources.img_logo
import java.awt.Dimension

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            state = rememberWindowState(width = 1280.dp, height = 900.dp),
            title = stringResource(Res.string.app_name),
            icon = painterResource(Res.drawable.img_logo)
        ) {
            window.minimumSize = Dimension(400, 300)
            ZzzArchiveApp()
        }
    }
}