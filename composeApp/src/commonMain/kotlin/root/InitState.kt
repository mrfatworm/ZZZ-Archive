package root

data class InitState(
    val isDark: Boolean = true,
    val uiScale: Float = 1f,
    val fontScale: Float = 1f,
    val appVersion: String = "",
    val isLoading: Boolean = true,
    /**
     * How many HoYoLab accounts the credential migration had to unlink because their stored
     * cookies could not be decrypted. Non-zero means the user has to link them again.
     */
    val unlinkedHoYoLabAccounts: Int = 0
)
