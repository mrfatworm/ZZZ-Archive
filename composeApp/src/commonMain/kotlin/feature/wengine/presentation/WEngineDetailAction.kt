package feature.wengine.presentation


sealed interface WEngineDetailAction {
    data object OnBackClick : WEngineDetailAction
    data object OnRetry : WEngineDetailAction
}