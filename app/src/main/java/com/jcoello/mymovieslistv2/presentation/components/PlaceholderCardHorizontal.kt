package com.jcoello.mymovieslistv2.presentation.components


import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.jcoello.mymovieslistv2.R

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PlaceholderCardHorizontal(
    modifier: Modifier = Modifier,
    color: Color = Color.Gray,
    highlightColor: Color = Color.White
) {
    Card(
        modifier = modifier
            .padding(
                start = dimensionResource(id = R.dimen.padding_24),
                end = dimensionResource(id = R.dimen.padding_24),
                top = dimensionResource(id = R.dimen.padding_12)
            )
            .clip(shape = RoundedCornerShape(4.dp))
            .placeholder(
                visible = true,
                color = color,
                shape = RoundedCornerShape(4.dp),
                highlight = PlaceholderHighlight.shimmer(
                    highlightColor = highlightColor,
                    animationSpec = InfiniteRepeatableSpec(
                        animation = tween(
                            durationMillis = 4000
                        )
                    )
                )
            )
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(4.dp)
            )
    ) {
        Box(modifier = Modifier.fillMaxSize(0.7f))
    }
}