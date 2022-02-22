package com.jcoello.mymovieslistv2.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.jcoello.mymovieslistv2.ui.theme.ColorChip
import com.jcoello.mymovieslistv2.ui.theme.ColorStar

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipElement(
    modifier: Modifier = Modifier,
    name: String,
    hasIcon: Boolean = false,
    icon: ImageVector = Icons.Filled.Star
) {
    Chip(
        modifier = modifier.wrapContentSize(),
        colors = ChipDefaults.chipColors(
            backgroundColor = ColorChip,
            contentColor = MaterialTheme.colorScheme.onSecondary,
        ),
        onClick = { }) {
        if (hasIcon) {
            Icon(
                imageVector = icon,
                tint = ColorStar,
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(start = 5.dp),
                text = name
            )
        } else {
            Text(text = name)
        }
    }
}