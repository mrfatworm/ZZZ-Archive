package feature.wengine.presentation


sealed interface WEngineDetailAction {
    data object ClickBack : WEngineDetailAction
    data object Retry : WEngineDetailAction
}