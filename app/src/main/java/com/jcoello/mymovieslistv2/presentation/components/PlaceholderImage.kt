package com.jcoello.mymovieslistv2.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.jcoello.mymovieslistv2.R

@Composable
fun PlaceholderImage(
    modifier: Modifier = Modifier,
    @DrawableRes imageId: Int = R.drawable.folder_empty
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .fillMaxHeight(0.7f)
                .scale(1f),
            painter = painterResource(id = imageId),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true, widthDp = 300, heightDp = 50)
@Composable
fun PlaceholderImagePreview() {
    PlaceholderImage()
}