package com.jcoello.mymovieslistv2.presentation.components

import android.os.Bundle
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.Navigator
import androidx.navigation.navArgument
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.PagerScope
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.jcoello.domain.model.Gender
import com.jcoello.domain.model.Movie
import com.jcoello.mymovieslistv2.App
import com.jcoello.mymovieslistv2.BuildConfig
import com.jcoello.mymovieslistv2.R
import com.jcoello.mymovieslistv2.presentation.navigation.Screen
import com.jcoello.mymovieslistv2.ui.theme.DarkColorPalette
import kotlin.math.absoluteValue

@OptIn(ExperimentalMaterial3Api::class, com.google.accompanist.pager.ExperimentalPagerApi::class)
@Composable
fun CardVerticalMovie(
    modifier: Modifier = Modifier,
    navController: NavController,
    movie: Movie,
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
                            posterPath = movie.posterPath,
                            title = movie.title,
                            adult = movie.adult,
                            isMovie = true,
                            gendersIds = movie.gendersIds,
                            overview = movie.overview,
                            voteAverage = movie.voteAverage
                        )
                    )
                }
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = rememberImagePainter(
                    data = BuildConfig.POSTER_URL + movie.posterPath
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
                if (movie.adult) {
                    ChipElement(name = stringResource(R.string.common_more_18_years))
                }
            }

            item {
                ChipElement(name = movie.voteAverage.toString(), hasIcon = true)
            }
            val gendersFiltered = mutableListOf<Gender>()
            movie.gendersIds.forEach { genderId ->
                val gender = App.moviesGendersList.first { gender ->
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
            text = movie.title,
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