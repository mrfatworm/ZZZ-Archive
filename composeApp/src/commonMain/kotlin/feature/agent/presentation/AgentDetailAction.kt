package feature.agent.presentation


sealed interface AgentDetailAction {
    data class OnWEngineClick(val wEngineId: Int) : AgentDetailAction
    data object OnBackClick : AgentDetailAction
    data object OnRetry : AgentDetailAction
}