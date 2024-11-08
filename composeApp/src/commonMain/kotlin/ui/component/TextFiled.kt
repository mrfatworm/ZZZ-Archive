/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import ui.theme.AppTheme

@Composable
fun ZzzTextFiled(
    modifier: Modifier, hint: String, value: String, onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    isError: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    interactionSource: MutableInteractionSource? = null,
) {
    OutlinedTextField(
        modifier = modifier.border(
            AppTheme.dimens.borderWidth,
            if (isError) AppTheme.colors.alert else AppTheme.colors.border,
            RoundedCornerShape(16.dp)
        ),
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = hint,
                style = AppTheme.typography.titleMedium,
                color = AppTheme.colors.onSurfaceVariant.copy(alpha = 0.7f)
            )
        },
        textStyle = AppTheme.typography.bodyLarge,
        enabled = enabled,
        isError = isError,
        maxLines = maxLines,
        minLines = minLines,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors().copy(
            unfocusedTextColor = AppTheme.colors.onSurface,
            unfocusedContainerColor = AppTheme.colors.surface,
            unfocusedIndicatorColor = AppTheme.colors.surface,
            focusedTextColor = AppTheme.colors.onSurface,
            focusedContainerColor = AppTheme.colors.surface,
            focusedIndicatorColor = AppTheme.colors.primary,
            cursorColor = AppTheme.colors.primary,
            errorTextColor = AppTheme.colors.onSurface,
            errorContainerColor = AppTheme.colors.surface,
            errorIndicatorColor = AppTheme.colors.alert,
        )
    )
}