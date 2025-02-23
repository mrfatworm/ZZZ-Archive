package feature.hoyolab.model

data class MyAgentsListState(
    val agentsList: List<MyAgentListItem> = emptyList(),
    val errorMessage: String = ""
)
