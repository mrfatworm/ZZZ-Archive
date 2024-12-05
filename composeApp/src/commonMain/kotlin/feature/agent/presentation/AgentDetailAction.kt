package feature.agent.presentation


sealed interface AgentDetailAction {
    data class ClickWEngine(val wEngineId: Int) : AgentDetailAction
    data object ClickBack : AgentDetailAction
    data object Retry : AgentDetailAction
}