/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.LocalUriHandler
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ui.components.buttons.ZzzIconButton
import ui.components.buttons.ZzzOutlineButton
import ui.components.cards.CardHeader
import ui.components.cards.ContentCard
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.copy_ltoken_ltuid_paste_them_into_the_form
import zzzarchive.composeapp.generated.resources.either_one_works
import zzzarchive.composeapp.generated.resources.feedback
import zzzarchive.composeapp.generated.resources.frequently_asked_questions
import zzzarchive.composeapp.generated.resources.hoyolab_zzz_game_record
import zzzarchive.composeapp.generated.resources.i_still_have_other_questions
import zzzarchive.composeapp.generated.resources.ic_github
import zzzarchive.composeapp.generated.resources.image_example
import zzzarchive.composeapp.generated.resources.img_hoyolab_guide_chrome
import zzzarchive.composeapp.generated.resources.img_hoyolab_guide_edge
import zzzarchive.composeapp.generated.resources.instruction_guide
import zzzarchive.composeapp.generated.resources.navigate_to_zzz_battle_record
import zzzarchive.composeapp.generated.resources.open_a_desktop_browser
import zzzarchive.composeapp.generated.resources.open_developer_tools
import zzzarchive.composeapp.generated.resources.please_try_again_using_incognito_mode
import zzzarchive.composeapp.generated.resources.press_f12_to_open_developer_tools_cookies
import zzzarchive.composeapp.generated.resources.provide_feedback_via_suggestions_or_report_your_issue_on_github
import zzzarchive.composeapp.generated.resources.there_are_two_ltoken_values_which_one_should_i_choose
import zzzarchive.composeapp.generated.resources.unable_to_link

@Composable
fun HoYoLabSyncGuildCard(navigateToFeedback: () -> Unit) {
    ContentCard(hasDefaultPadding = false) {
        CardHeader(stringResource(Res.string.instruction_guide))
        Column(
            modifier = Modifier.fillMaxWidth().padding(
                start = AppTheme.spacing.s400,
                end = AppTheme.spacing.s400,
                top = AppTheme.spacing.s300,
                bottom = AppTheme.spacing.s400
            ), verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s450)
        ) {
            Text(
                text = "1. " + stringResource(Res.string.open_a_desktop_browser),
                color = AppTheme.colors.onSurfaceVariant,
                style = AppTheme.typography.labelMedium
            )
            StepTwo()
            StepThree()
            FrequentQuestions(navigateToFeedback)
        }
    }
}

@Composable
private fun StepTwo() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s400)
    ) {
        Text(
            text = "2. " + stringResource(Res.string.navigate_to_zzz_battle_record),
            color = AppTheme.colors.onSurfaceVariant,
            style = AppTheme.typography.labelMedium
        )
        Column(
            modifier = Modifier.fillMaxWidth().padding(start = AppTheme.spacing.s400),
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)
        ) {
            val url = "https://act.hoyolab.com/app/zzz-game-record/index.html#/zzz"
            val urlHandler = LocalUriHandler.current
            Text(
                modifier = Modifier.pointerHoverIcon(PointerIcon.Hand).clickable {
                    urlHandler.openUri(url)
                },
                text = url,
                color = AppTheme.colors.primary,
                style = AppTheme.typography.bodySmall
            )
            Text(
                text = stringResource(Res.string.hoyolab_zzz_game_record),
                color = AppTheme.colors.onSurfaceVariant,
                style = AppTheme.typography.bodySmall
            )
        }
    }
}

@Composable
private fun StepThree() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s400)
    ) {
        Text(
            text = "3. " + stringResource(Res.string.open_developer_tools),
            color = AppTheme.colors.onSurfaceVariant,
            style = AppTheme.typography.labelMedium
        )
        Column(
            modifier = Modifier.fillMaxWidth().padding(start = AppTheme.spacing.s400),
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)
        ) {
            Text(
                text = stringResource(Res.string.press_f12_to_open_developer_tools_cookies),
                color = AppTheme.colors.onSurfaceVariant,
                style = AppTheme.typography.bodySmall
            )
            Text(
                text = stringResource(Res.string.copy_ltoken_ltuid_paste_them_into_the_form),
                color = AppTheme.colors.onSurfaceVariant,
                style = AppTheme.typography.bodySmall
            )
            Text(
                text = "Chrome:",
                color = AppTheme.colors.onSurfaceVariant,
                style = AppTheme.typography.bodySmall
            )
            Image(
                modifier = Modifier.aspectRatio(1.42f).fillMaxWidth(),
                painter = painterResource(Res.drawable.img_hoyolab_guide_chrome),
                contentDescription = stringResource(Res.string.image_example)
            )
            Text(
                text = "Edge:",
                color = AppTheme.colors.onSurfaceVariant,
                style = AppTheme.typography.bodySmall
            )
            Image(
                modifier = Modifier.aspectRatio(1.42f).fillMaxWidth(),
                painter = painterResource(Res.drawable.img_hoyolab_guide_edge),
                contentDescription = stringResource(Res.string.image_example)
            )
        }
    }
}

@Composable
fun FrequentQuestions(navigateToFeedback: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s400)
    ) {
        Text(
            text = stringResource(Res.string.frequently_asked_questions),
            color = AppTheme.colors.onSurfaceVariant,
            style = AppTheme.typography.labelMedium
        )
        Column(
            modifier = Modifier.fillMaxWidth().padding(start = AppTheme.spacing.s400),
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)
        ) {
            Text(
                text = "Q: " + stringResource(Res.string.unable_to_link),
                color = AppTheme.colors.onSurfaceVariant,
                style = AppTheme.typography.bodySmall
            )
            Text(
                text = "A: " + stringResource(Res.string.please_try_again_using_incognito_mode),
                color = AppTheme.colors.onSurfaceVariant,
                style = AppTheme.typography.bodySmall
            )
            Text(
                text = "Q: " + stringResource(Res.string.there_are_two_ltoken_values_which_one_should_i_choose),
                color = AppTheme.colors.onSurfaceVariant,
                style = AppTheme.typography.bodySmall
            )
            Text(
                text = "A: " + stringResource(Res.string.either_one_works),
                color = AppTheme.colors.onSurfaceVariant,
                style = AppTheme.typography.bodySmall
            )
            Text(
                text = "Q: " + stringResource(Res.string.i_still_have_other_questions),
                color = AppTheme.colors.onSurfaceVariant,
                style = AppTheme.typography.bodySmall
            )
            Text(
                text = "A: " + stringResource(Res.string.provide_feedback_via_suggestions_or_report_your_issue_on_github),
                color = AppTheme.colors.onSurfaceVariant,
                style = AppTheme.typography.bodySmall
            )
        }
    }
    val urlHandler = LocalUriHandler.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300),
        verticalAlignment = CenterVertically
    ) {
        ZzzOutlineButton(
            modifier = Modifier.weight(1f), text = stringResource(Res.string.feedback)
        ) {
            navigateToFeedback()
        }
        ZzzIconButton(iconRes = Res.drawable.ic_github) {
            urlHandler.openUri("https://github.com/mrfatworm/ZZZ-Archive")
        }
    }
}