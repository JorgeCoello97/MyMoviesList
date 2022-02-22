package com.jcoello.mymovieslistv2.presentation.components

import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.layout.lerp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class, androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun PlaceholderCardVertical(
    modifier: Modifier = Modifier,
    color: Color = Color.Gray,
    highlightColor: Color = Color.White
) {
    val pagerState = rememberPagerState()
    LaunchedEffect(true) {
        pagerState.scrollToPage(1)
    }

    HorizontalPager(
        modifier = modifier,
        count = 3,
        contentPadding = PaddingValues(
            horizontal = 75.dp
        ),
        state = pagerState,
        userScrollEnabled = false
    ) { page ->
        Column(
            modifier = Modifier.fillMaxHeight(0.9f)
                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                    lerp(
                        start = ScaleFactor(scaleX = 0.85f, scaleY = 0.85f),
                        stop = ScaleFactor(scaleX = 1f, scaleY = 1f),
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale.scaleX
                        scaleY = scale.scaleY
                    }
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(5.dp))
            Card(
                modifier = Modifier
                    .weight(0.7f, true)
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
                Box(modifier = Modifier.fillMaxSize())
            }
            Spacer(modifier = Modifier.size(5.dp))
            Row(
                modifier = Modifier.alpha(0f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ChipElement(name = "")
                ChipElement(name = "")
                ChipElement(name = "")
            }
            Text(
                modifier = Modifier.alpha(0f),
                text = "",
                style = MaterialTheme.typography.displayMedium.copy(fontSize = 20.sp)
            )
            Spacer(modifier = Modifier.size(20.dp))
        }
    }
}