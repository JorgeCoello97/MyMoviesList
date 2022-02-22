package com.jcoello.mymovieslistv2.presentation.components


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.layout.lerp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.PagerScope
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.jcoello.domain.model.Gender
import com.jcoello.domain.model.Serie
import com.jcoello.mymovieslistv2.App
import com.jcoello.mymovieslistv2.BuildConfig
import com.jcoello.mymovieslistv2.presentation.navigation.Screen
import com.jcoello.mymovieslistv2.ui.theme.DarkColorPalette
import kotlin.math.absoluteValue

@OptIn(ExperimentalMaterial3Api::class, com.google.accompanist.pager.ExperimentalPagerApi::class)
@Composable
fun CardVerticalSerie(
    modifier: Modifier = Modifier,
    navController: NavController,
    serie: Serie,
    page: Int,
    pagerScope: PagerScope
) {
    val visibleState = remember {
        mutableStateOf(false)
    }
    val animateVisibility = animateFloatAsState(targetValue = if (visibleState.value) 1f else 0f)
    Column(
        modifier = modifier
            .fillMaxHeight(0.9f)
            .graphicsLayer {
                val pageOffset = pagerScope.calculateCurrentOffsetForPage(page).absoluteValue

                lerp(
                    start = ScaleFactor(scaleX = 0.85f, scaleY = 0.85f),
                    stop = ScaleFactor(scaleX = 1f, scaleY = 1f),
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                ).also { scale ->
                    scaleX = scale.scaleX
                    scaleY = scale.scaleY
                    visibleState.value = scaleX == 1f && scaleY == 1f
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(5.dp))
        Card(
            modifier = Modifier
                .weight(0.7f, true)
                .clip(shape = RoundedCornerShape(4.dp))
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(4.dp)
                )
                .clickable {
                    navController.navigate(
                        route = Screen.Details.mountRouteDetails(
                            posterPath = serie.posterPath,
                            title = serie.title,
                            adult = false,
                            isMovie = true,
                            gendersIds = serie.gendersIds,
                            overview = serie.overview,
                            voteAverage = serie.voteAverage
                        )
                    )
                }
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberImagePainter(
                    data = BuildConfig.POSTER_URL + serie.posterPath
                ),
                contentScale = ContentScale.FillBounds,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.size(5.dp))
        LazyRow(
            modifier = Modifier.alpha(animateVisibility.value),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            item {
                ChipElement(name = serie.voteAverage.toString(), hasIcon = true)
            }
            val gendersFiltered = mutableListOf<Gender>()
            serie.gendersIds.forEach { genderId ->
                val gender = App.seriesGendersList.first { gender ->
                    gender.id == genderId
                }
                gendersFiltered.add(gender)
            }
            items(gendersFiltered) { gender ->
                ChipElement(
                    modifier = Modifier.padding(horizontal = 5.dp),
                    name = gender.name
                )
            }

        }
        Text(
            modifier = Modifier.alpha(animateVisibility.value),
            text = serie.title,
            textAlign = TextAlign.Center,
            color = DarkColorPalette.onPrimary,
            fontWeight = FontWeight.W400,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.displayMedium.copy(fontSize = 20.sp)
        )
        Spacer(modifier = Modifier.size(20.dp))
    }
}