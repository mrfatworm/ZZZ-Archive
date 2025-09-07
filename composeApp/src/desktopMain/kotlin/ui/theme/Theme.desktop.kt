/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.formdev.flatlaf.FlatDarkLaf
import com.formdev.flatlaf.FlatLightLaf
import java.util.Locale
import javax.swing.SwingUtilities
import javax.swing.UIManager

@Composable
internal actual fun SystemAppearance(isDark: Boolean) {
    LaunchedEffect(isDark) {
        val osName = System.getProperty("os.name", "unknown").lowercase(Locale.ROOT)
        if (osName.contains("mac")) {
            val propertyName = "apple.awt.application.appearance"
            val currentAppearance = System.getProperty(propertyName)
            val newAppearance = if (isDark) "NSAppearanceNameDarkAqua" else "NSAppearanceNameAqua"

            if (currentAppearance != newAppearance) {
                System.setProperty(propertyName, newAppearance)
            }
        } else {
            try {
                if (isDark) {
                    UIManager.setLookAndFeel(FlatDarkLaf())
                } else {
                    UIManager.setLookAndFeel(FlatLightLaf())
                }
                for (window in java.awt.Window.getWindows()) {
                    SwingUtilities.updateComponentTreeUI(window)
                }
            } catch (e: Exception) {
                println("Failed to set LookAndFeel: ${e.message}")
                e.printStackTrace()
            }
        }
    }
}
