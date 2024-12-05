package feature.bangboo.presentation


sealed interface BangbooDetailAction {
    data object ClickBack : BangbooDetailAction
    data object Retry : BangbooDetailAction
}