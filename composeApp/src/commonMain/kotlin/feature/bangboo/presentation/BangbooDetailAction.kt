package feature.bangboo.presentation


sealed interface BangbooDetailAction {
    data object OnBackClick : BangbooDetailAction
    data object OnRetry : BangbooDetailAction
}