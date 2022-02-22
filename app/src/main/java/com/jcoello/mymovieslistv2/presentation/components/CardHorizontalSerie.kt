package com.jcoello.mymovieslistv2.presentation.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerScope
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.jcoello.domain.model.Serie
import com.jcoello.mymovieslistv2.BuildConfig
import com.jcoello.mymovieslistv2.presentation.navigation.Screen
import com.jcoello.mymovieslistv2.ui.theme.DarkColorPalette

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CardHorizontalSerie(
    modifier: Modifier = Modifier,
    navController: NavController,
    serie: Serie,
    page: Int,
    pagerScope: PagerScope
) {
    Card(
        modifier = modifier
            .fillMaxSize(0.7f)
            .graphicsLayer {
                val pageOffSet = pagerScope.calculateCurrentOffsetForPage(page)
                alpha = lerp(
                    start = ScaleFactor(
                        scaleX = 0f,
                        scaleY = 0f
                    ),
                    stop = ScaleFactor(
                        scaleX = 1f,
                        scaleY = 1f
                    ),
                    fraction = 1f - pageOffSet.coerceIn(0f, 1f)
                ).scaleX
            }
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
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = rememberImagePainter(
                    data = BuildConfig.POSTER_URL + serie.bannerPath
                ),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Text(
                modifier = Modifier
                    .padding(start = 10.dp, top = 5.dp)
                    .align(Alignment.TopStart),
                text = serie.title,
                textAlign = TextAlign.Center,
                color = DarkColorPalette.onPrimary,
                fontWeight = FontWeight.W400,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.displayMedium.copy(fontSize = 18.sp)
            )
        }
    }
}