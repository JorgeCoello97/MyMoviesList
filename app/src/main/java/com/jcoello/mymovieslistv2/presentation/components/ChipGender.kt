package com.jcoello.mymovieslistv2.presentation.components

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jcoello.mymovieslistv2.ui.theme.ColorAccent
import com.jcoello.mymovieslistv2.ui.theme.ColorChip

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipGender(
    modifier: Modifier = Modifier,
    isActivated: Boolean,
    text: String,
    enabled: Boolean,
    onClick: () -> Unit = {}
) {
    Chip(
        modifier = modifier.wrapContentSize(),
        colors = ChipDefaults.chipColors(
            backgroundColor = if (isActivated) ColorAccent else ColorChip,
            contentColor = MaterialTheme.colorScheme.onSecondary,
        ),
        enabled = enabled,
        onClick = onClick
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true, widthDp = 300, heightDp = 50)
@Composable
fun ChipGenderActivatedPreview() {
    ChipGender(
        isActivated = true,
        text = "PRUEBA",
        enabled = true
    )
}

@Preview(showBackground = true, widthDp = 300, heightDp = 50)
@Composable
fun ChipGenderNoActivatedPreview() {
    ChipGender(
        isActivated = false,
        text = "PRUEBA",
        enabled = true
    )
}
