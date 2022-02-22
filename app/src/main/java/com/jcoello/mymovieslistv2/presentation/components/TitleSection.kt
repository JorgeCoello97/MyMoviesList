package com.jcoello.mymovieslistv2.presentation.components

import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.jcoello.mymovieslistv2.R
import com.jcoello.mymovieslistv2.ui.theme.DarkColorPalette

@Composable
fun TitleSection(
    modifier: Modifier = Modifier,
    title: String
) {
    Text(
        modifier = modifier,
        color = DarkColorPalette.onPrimary,
        text = title,
        fontWeight = FontWeight.W400,
        style = MaterialTheme.typography.titleLarge
    )
}

@Preview(showBackground = true, widthDp = 300, heightDp = 50)
@Composable
fun TitleSectionPreview() {
    TitleSection(
        title = stringResource(id = R.string.common_coming_soon)
    )
}