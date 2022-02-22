package com.jcoello.mymovieslistv2.presentation.screens.details

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.jcoello.domain.model.Gender
import com.jcoello.mymovieslistv2.App
import com.jcoello.mymovieslistv2.BuildConfig
import com.jcoello.mymovieslistv2.R
import com.jcoello.mymovieslistv2.presentation.components.ChipElement
import com.jcoello.mymovieslistv2.presentation.components.TitleSection
import com.jcoello.mymovieslistv2.ui.theme.DarkColorPalette
import java.util.*

@Composable
fun DetailsScreen(
    navController: NavController,
    posterPath: String,
    title: String,
    adult: Boolean,
    isMovie: Boolean,
    gendersIdsTokenized: String,
    overview: String,
    voteAverage: Float
) {
    val scrollState = rememberScrollState()
    val tokenizer = StringTokenizer(gendersIdsTokenized, ".")
    val genderIdsList = mutableListOf<Int>()
    while (tokenizer.hasMoreTokens()) {
        val genderId = tokenizer.nextToken()
        genderIdsList.add(genderId.toInt())
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        PosterSection(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.6f),
            navController = navController,
            posterPath = posterPath
        )
        DescriptionSection(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.4f),
            title = title,
            adult = adult,
            isMovie = isMovie,
            gendersIdsList = genderIdsList,
            overview = overview,
            voteAverage = voteAverage,
            scrollState = scrollState
        )
    }
}

@Composable
fun PosterSection(
    modifier: Modifier = Modifier,
    navController: NavController,
    posterPath: String
) {
    Box(modifier = modifier) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = rememberImagePainter(
                data = BuildConfig.POSTER_URL + "/" + posterPath
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, MaterialTheme.colorScheme.background)
                    )
                )
        )
        IconButton(
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.padding_24),
                    top = dimensionResource(id = R.dimen.padding_48)
                )
                .align(Alignment.TopStart),
            onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}


@Composable
fun DescriptionSection(
    modifier: Modifier = Modifier,
    title: String,
    adult: Boolean,
    isMovie: Boolean,
    gendersIdsList: MutableList<Int>,
    overview: String,
    voteAverage: Float,
    scrollState: ScrollState
) {
    val genderIdsList = gendersIdsList?.toList()
    val gendersList = mutableListOf<Gender>()
    val gendersBase = mutableListOf<Gender>()
    if (isMovie) {
        gendersBase.addAll(App.moviesGendersList)
    } else {
        gendersBase.addAll(App.seriesGendersList)
    }

    gendersList.addAll(gendersBase.filter { gender -> genderIdsList.contains(gender.id) })

    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .padding(
                PaddingValues(
                    all = dimensionResource(id = R.dimen.padding_24)
                )
            )
    ) {
        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            if (adult) {
                item {
                    ChipElement(name = stringResource(id = R.string.common_more_18_years))
                }
            }
            items(gendersList) { gender ->
                ChipElement(name = gender.name)
            }
            item {
                ChipElement(
                    name = voteAverage.toString(),
                    hasIcon = true
                )
            }
        }
        TitleSection(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen.padding_24),
                    bottom = dimensionResource(id = R.dimen.padding_12)
                ),
            title = title
        )
        Text(
            text = overview,
            color = DarkColorPalette.onPrimary,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.size(50.dp))
    }
}
