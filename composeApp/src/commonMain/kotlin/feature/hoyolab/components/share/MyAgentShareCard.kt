/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.hoyolab.components.share

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import feature.hoyolab.components.agent.WeaponStars
import feature.hoyolab.components.agent.paintingImageRequest
import feature.hoyolab.model.agent.EquipPlanProperty
import feature.hoyolab.model.agent.MyAgentDetail
import feature.hoyolab.model.agent.MyAgentDetailEquip
import feature.hoyolab.model.agent.MyAgentDetailEquipPlan
import feature.hoyolab.model.agent.MyAgentDetailProperty
import feature.hoyolab.model.agent.MyAgentDetailState
import feature.hoyolab.model.agent.MyAgentDetailWeapon
import feature.hoyolab.model.agent.MyAgentEquipSuit
import feature.hoyolab.model.agent.getEquipRatingState
import kotlin.math.roundToInt
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.components.OutlinedText
import ui.components.cards.ContentCard
import ui.theme.AppTheme
import ui.utils.parseHexColor
import utils.AgentAttribute
import utils.AgentSpecialty
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.app_name
import zzzarchive.composeapp.generated.resources.effective_sub_stats
import zzzarchive.composeapp.generated.resources.ic_do_not_disturb_on
import zzzarchive.composeapp.generated.resources.w_engine_not_equipped

/** The card's own dp space; the dialog composes it at a fixed density so the capture is 1080px wide. */
val SHARE_CARD_WIDTH = 540.dp
private val HERO_HEIGHT = 300.dp
private val CARD_PADDING = 20.dp
private val SECTION_GAP = 12.dp
private const val DRIVE_SLOTS = 6
private const val DRIVE_COLUMNS = 3

/**
 * The picture that gets shared: the agent's painting, W-Engine, drive discs, stats and build
 * score on one page. Reads only [AppTheme] tokens, so the dialog can hand it a palette and a
 * scale independent of the screen's.
 */
@Composable
fun MyAgentShareCard(
    modifier: Modifier = Modifier,
    uiState: MyAgentDetailState,
    tracker: ShareImageTracker,
    date: String
) {
    val agent = uiState.agentDetail
    val selectedSkin = agent.skins.firstOrNull { it.id == uiState.selectedSkinId }
    val paintingUrl =
        if (uiState.isCustomImage) {
            uiState.customImgUrl
        } else {
            selectedSkin?.imageUrl?.ifEmpty { agent.imageUrl } ?: agent.imageUrl
        }
    val paintingColor = selectedSkin?.paintingColor?.ifEmpty { agent.paintingColor } ?: agent.paintingColor
    val accent = parseHexColor(paintingColor) ?: agent.attribute.getColor(AppTheme.colors)

    Column(modifier = modifier.width(SHARE_CARD_WIDTH).background(AppTheme.colors.surface)) {
        ShareCardHero(
            agent = agent,
            paintingUrl = paintingUrl,
            isCustomImage = uiState.isCustomImage,
            accent = accent,
            tracker = tracker
        )
        Column(
            modifier =
                Modifier
                    .padding(horizontal = CARD_PADDING)
                    .padding(top = SECTION_GAP, bottom = CARD_PADDING),
            verticalArrangement = Arrangement.spacedBy(SECTION_GAP)
        ) {
            ShareCardWeaponRow(weapon = agent.weapon, equipPlan = agent.equipPlanInfo, tracker = tracker)
            ShareCardStatsGrid(properties = agent.properties, planProperties = uiState.planProperties)
            ShareCardDriveGrid(
                drives = agent.equip,
                showDriveDetails = uiState.shareOptions.showDriveDetails,
                tracker = tracker
            )
            ShareCardFooter(
                uid = uiState.uid.takeIf { uiState.showUid },
                author = uiState.customImgAuthor,
                date = date
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun ShareCardHero(
    agent: MyAgentDetail,
    paintingUrl: String,
    isCustomImage: Boolean,
    accent: Color,
    tracker: ShareImageTracker
) {
    val surface = AppTheme.colors.surface
    Column {
        Box(modifier = Modifier.fillMaxWidth().height(HERO_HEIGHT)) {
            if (paintingUrl.isNotEmpty()) {
                TrackedAsyncImage(
                    request = paintingImageRequest(url = paintingUrl, isCustomImage = isCustomImage),
                    tracker = tracker,
                    modifier = Modifier.matchParentSize(),
                    contentScale = ContentScale.Crop
                )
            }
            // Fade the painting into the page so the name stays legible on any artwork.
            Box(
                modifier =
                    Modifier
                        .matchParentSize()
                        .background(Brush.verticalGradient(0.45f to Color.Transparent, 1f to surface))
            )
            Column(
                modifier =
                    Modifier
                        .align(Alignment.BottomStart)
                        .padding(horizontal = CARD_PADDING, vertical = SECTION_GAP),
                verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)
            ) {
                OutlinedText(
                    text = agent.name,
                    color = AppTheme.colors.onSurfaceContainer,
                    style = AppTheme.typography.headlineLarge,
                    borderColor = AppTheme.colors.surfaceLow,
                    borderDrawStyle = Stroke(width = 8f, join = StrokeJoin.Round)
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ShareChip(text = "Lv. ${agent.level}")
                    ShareChip(text = "M${agent.rank}")
                    ShareChip(
                        text = agent.rarity.code,
                        container = agent.rarity.getColor(AppTheme.colors),
                        content = AppTheme.colors.surface
                    )
                    if (agent.attribute != AgentAttribute.None) {
                        Icon(
                            modifier = Modifier.size(AppTheme.size.iconLarge),
                            imageVector = vectorResource(agent.attribute.iconRes),
                            contentDescription = null,
                            tint = agent.attribute.getColor(AppTheme.colors)
                        )
                    }
                    if (agent.specialty != AgentSpecialty.None) {
                        Icon(
                            modifier = Modifier.size(AppTheme.size.iconLarge),
                            imageVector = vectorResource(agent.specialty.iconRes),
                            contentDescription = null,
                            tint = AppTheme.colors.onSurfaceContainer
                        )
                    }
                    if (agent.factionImageUrl.isNotEmpty()) {
                        TrackedAsyncImage(
                            uri = agent.factionImageUrl,
                            tracker = tracker,
                            modifier = Modifier.size(AppTheme.size.iconLarge)
                        )
                    }
                }
            }
        }
        // HoYoLab's accent for this painting, as a stripe under it.
        Box(modifier = Modifier.fillMaxWidth().height(4.dp).background(accent))
    }
}

@Composable
private fun ShareChip(
    text: String,
    container: Color = AppTheme.colors.onSurfaceVariant,
    content: Color = AppTheme.colors.surfaceContainer
) {
    Text(
        modifier =
            Modifier
                .clip(AppTheme.shape.r300)
                .background(container)
                .padding(horizontal = AppTheme.spacing.s300, vertical = AppTheme.spacing.s100),
        text = text,
        color = content,
        style = AppTheme.typography.labelMedium
    )
}

@Composable
private fun ShareCardWeaponRow(
    weapon: MyAgentDetailWeapon,
    equipPlan: MyAgentDetailEquipPlan,
    tracker: ShareImageTracker
) {
    ContentCard {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s400),
            verticalAlignment = Alignment.CenterVertically
        ) {
            when (weapon) {
                is MyAgentDetailWeapon.MyAgentWeapon -> ShareWeapon(Modifier.weight(1f), weapon, tracker)
                MyAgentDetailWeapon.Empty -> ShareWeaponEmpty(Modifier.weight(1f))
            }
            if (equipPlan is MyAgentDetailEquipPlan.MyAgentEquipPlan) {
                ShareScore(equipPlan)
            }
        }
    }
}

@Composable
private fun ShareWeapon(
    modifier: Modifier,
    weapon: MyAgentDetailWeapon.MyAgentWeapon,
    tracker: ShareImageTracker
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s400),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (weapon.iconUrl.isNotEmpty()) {
            TrackedAsyncImage(uri = weapon.iconUrl, tracker = tracker, modifier = Modifier.size(AppTheme.size.s72))
        }
        Column(verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s200)) {
            Text(
                text = weapon.name,
                color = AppTheme.colors.onSurfaceContainer,
                style = AppTheme.typography.labelLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ShareChip(text = "Lv ${weapon.level}")
                WeaponStars(star = weapon.star, iconSize = AppTheme.size.iconSmall)
            }
            val stats = (weapon.mainProperties + weapon.properties).map { "${it.name} ${it.base}" }
            if (stats.isNotEmpty()) {
                Text(
                    text = stats.joinToString(" · "),
                    color = AppTheme.colors.onSurfaceVariant,
                    style = AppTheme.typography.bodySmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun ShareWeaponEmpty(modifier: Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s400),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(AppTheme.size.s40),
            imageVector = vectorResource(Res.drawable.ic_do_not_disturb_on),
            contentDescription = null,
            tint = AppTheme.colors.onSurfaceVariant
        )
        Text(
            text = stringResource(Res.string.w_engine_not_equipped),
            color = AppTheme.colors.onSurfaceVariant,
            style = AppTheme.typography.labelLarge
        )
    }
}

@Composable
private fun ShareScore(equipPlan: MyAgentDetailEquipPlan.MyAgentEquipPlan) {
    // Same rule as the detail screen: no block at all for ER_Default.
    val rating = getEquipRatingState(equipPlan.equipRating) ?: return
    // The letter beside the numbers, not above them: the row must stay as short as the W-Engine.
    Row(
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = rating.symbol, color = rating.color, style = AppTheme.typography.scoreRegular)
        Column(verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s100)) {
            equipPlan.equipRatingScore?.let { score ->
                Text(
                    text = formatScore(score),
                    color = AppTheme.colors.onSurfaceContainer,
                    style = AppTheme.typography.labelLarge
                )
            }
            Text(
                text = "${stringResource(Res.string.effective_sub_stats)} ${equipPlan.validPropertyCnt}",
                color = AppTheme.colors.onSurfaceVariant,
                style = AppTheme.typography.bodySmall
            )
        }
    }
}

/** One decimal, without `String.format`, which common code does not have. */
private fun formatScore(score: Double): String {
    val tenths = (score * 10).roundToInt()
    return "${tenths / 10}.${tenths % 10}"
}

@Composable
private fun ShareCardDriveGrid(
    drives: List<MyAgentDetailEquip>,
    showDriveDetails: Boolean,
    tracker: ShareImageTracker
) {
    if (drives.isEmpty()) return
    if (!showDriveDetails) {
        ShareSuitSummary(drives = drives, tracker = tracker)
        return
    }
    // HoYoLab numbers the slots 1..6 in equipment_type; a slot with no disc shows as empty.
    val bySlot = drives.associateBy { it.equipmentType }
    Column(verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)) {
        for (row in 0 until DRIVE_SLOTS / DRIVE_COLUMNS) {
            Row(
                // Intrinsic height so a disc with fewer sub stats does not leave its neighbour taller.
                modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)
            ) {
                for (column in 0 until DRIVE_COLUMNS) {
                    val slot = row * DRIVE_COLUMNS + column + 1
                    val drive = bySlot[slot]
                    if (drive == null) {
                        ShareDriveEmpty(modifier = Modifier.weight(1f), slot = slot)
                    } else {
                        ShareDriveItem(
                            modifier = Modifier.weight(1f),
                            slot = slot,
                            drive = drive,
                            tracker = tracker
                        )
                    }
                }
            }
        }
        ShareSuitSummary(drives = drives, tracker = tracker)
    }
}

@Composable
private fun ShareDriveItem(
    modifier: Modifier,
    slot: Int,
    drive: MyAgentDetailEquip,
    tracker: ShareImageTracker
) {
    ContentCard(modifier = modifier.fillMaxHeight(), hasDefaultPadding = false) {
        Row(
            modifier = Modifier.padding(AppTheme.spacing.s300),
            horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (drive.iconUrl.isNotEmpty()) {
                TrackedAsyncImage(uri = drive.iconUrl, tracker = tracker, modifier = Modifier.size(AppTheme.size.s40))
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s100)
            ) {
                Text(
                    text = drive.name,
                    color = AppTheme.colors.onSurfaceVariant,
                    style = AppTheme.typography.labelSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "[$slot] Lv ${drive.level}",
                    color = AppTheme.colors.onSurfaceContainer,
                    style = AppTheme.typography.labelSmall
                )
            }
        }
        drive.mainProperties.firstOrNull()?.let { main ->
            SharePropertyRow(
                title = main.name,
                value = main.base,
                emphasized = true,
                background = AppTheme.colors.itemVariant
            )
        }
        for (sub in drive.subProperties) {
            // `level` is the roll count, so "+n" is how many upgrades landed on this stat.
            val rolls = if (sub.level > 1) " +${sub.level - 1}" else ""
            SharePropertyRow(title = sub.name + rolls, value = sub.base, highlight = sub.valid)
        }
    }
}

@Composable
private fun ShareDriveEmpty(
    modifier: Modifier,
    slot: Int
) {
    ContentCard(modifier = modifier.fillMaxHeight().heightIn(min = AppTheme.size.s64), hasDefaultPadding = false) {
        Row(
            modifier = Modifier.fillMaxHeight().padding(AppTheme.spacing.s300),
            horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(AppTheme.size.s40),
                imageVector = vectorResource(Res.drawable.ic_do_not_disturb_on),
                contentDescription = null,
                tint = AppTheme.colors.onSurfaceVariant
            )
            Text(
                text = "[$slot]",
                color = AppTheme.colors.onSurfaceVariant,
                style = AppTheme.typography.labelSmall
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ShareSuitSummary(
    drives: List<MyAgentDetailEquip>,
    tracker: ShareImageTracker
) {
    // One entry per set, carrying the icon of a disc from it as the chip's picture.
    val suits =
        drives
            .mapNotNull { drive -> (drive.equipSuit as? MyAgentEquipSuit.EquipSuit)?.let { it to drive.iconUrl } }
            .distinctBy { (suit, _) -> suit.suitId }
            .filter { (suit, _) -> suit.own >= 2 }
            .sortedByDescending { (suit, _) -> suit.own }
    if (suits.isEmpty()) return
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300),
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s200)
    ) {
        for ((suit, iconUrl) in suits) {
            Row(
                modifier =
                    Modifier
                        .clip(AppTheme.shape.r300)
                        .background(AppTheme.colors.surfaceContainer)
                        .padding(horizontal = AppTheme.spacing.s300, vertical = AppTheme.spacing.s200),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (iconUrl.isNotEmpty()) {
                    TrackedAsyncImage(
                        uri = iconUrl,
                        tracker = tracker,
                        modifier = Modifier.size(AppTheme.size.iconLarge)
                    )
                }
                Text(
                    text = "${suit.name} ×${suit.own}",
                    color = AppTheme.colors.onSurfaceContainer,
                    style = AppTheme.typography.labelMedium
                )
            }
        }
    }
}

@Composable
private fun ShareCardStatsGrid(
    properties: List<MyAgentDetailProperty>,
    planProperties: List<EquipPlanProperty>
) {
    if (properties.isEmpty()) return
    val half = (properties.size + 1) / 2
    val highlighted = planProperties.map { it.name }.toSet()
    ContentCard(hasDefaultPadding = false) {
        Row(modifier = Modifier.fillMaxWidth()) {
            for (column in listOf(properties.take(half), properties.drop(half))) {
                Column(modifier = Modifier.weight(1f)) {
                    column.forEachIndexed { index, property ->
                        SharePropertyRow(
                            title = property.name,
                            value = property.final,
                            highlight = property.name in highlighted,
                            emphasized = true,
                            background = if (index % 2 == 0) AppTheme.colors.itemVariant else Color.Transparent
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SharePropertyRow(
    title: String,
    value: String,
    highlight: Boolean = false,
    emphasized: Boolean = false,
    background: Color = Color.Transparent
) {
    val color =
        when {
            highlight -> AppTheme.colors.rarityS
            emphasized -> AppTheme.colors.onSurfaceContainer
            else -> AppTheme.colors.onSurfaceVariant
        }
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(background)
                .padding(horizontal = AppTheme.spacing.s300, vertical = AppTheme.spacing.s100),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s200),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            color = color,
            style = if (emphasized) AppTheme.typography.labelSmall else AppTheme.typography.bodySmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = value,
            color = color,
            style = if (highlight || emphasized) AppTheme.typography.labelSmall else AppTheme.typography.bodySmall
        )
    }
}

@Composable
private fun ShareCardFooter(
    uid: String?,
    author: String,
    date: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s100)) {
            Text(
                text = stringResource(Res.string.app_name),
                color = AppTheme.colors.onSurfaceVariant,
                style = AppTheme.typography.titleSmall
            )
            Text(text = date, color = AppTheme.colors.onSurfaceVariant, style = AppTheme.typography.bodySmall)
        }
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s100)
        ) {
            if (uid != null) {
                Text(
                    text = "UID $uid",
                    color = AppTheme.colors.onSurfaceContainer,
                    style = AppTheme.typography.labelMedium
                )
            }
            // A custom painting is someone's art; the credit the user typed goes on the picture.
            if (author.isNotEmpty()) {
                Text(text = author, color = AppTheme.colors.onSurfaceVariant, style = AppTheme.typography.bodySmall)
            }
        }
    }
}
