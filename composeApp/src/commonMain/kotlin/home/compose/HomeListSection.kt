/*
 *  Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 *  License: CC BY-SA 4.0
 */

package home.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ui.data.SimpleListItemState

@Composable
fun HomeListSection(
    titleText: String,
    items: List<SimpleListItemState>,
    onSeeAllClick: () -> Unit,
    onItemClick: (Long) -> Unit,
) {
    Column(verticalArrangement = Arrangement.Center) {
        Text("Home List")
    }
}
