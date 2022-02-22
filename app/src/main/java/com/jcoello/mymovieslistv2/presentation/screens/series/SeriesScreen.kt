package com.jcoello.mymovieslistv2.presentation.screens.series

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.jcoello.domain.model.Serie
import com.jcoello.domain.util.Pager
import com.jcoello.domain.util.Results
import com.jcoello.mymovieslistv2.App
import com.jcoello.mymovieslistv2.R
import com.jcoello.mymovieslistv2.presentation.components.*


@Composable
fun SeriesScreen(
    navController: NavController,
    selectedTabIndexState: MutableState<Int>
) {
    val seriesViewModel: SeriesViewModel = hiltViewModel()
    val scrollState = rememberScrollState()
    val selectedGenderIndexState = remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        TabsBar(
            modifier = Modifier
                .height(56.dp)
                .padding(horizontal = 50.dp),
            navController = navController,
            selectedTabIndexState = selectedTabIndexState,
            tabList = App.tabsList
        )
        TopRatedSeriesSection(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.3f),
            navController = navController,
            seriesViewModel = seriesViewModel,
            selectedGenderIndexState = selectedGenderIndexState
        )
        PopularSeriesSection(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f),
            navController = navController,
            seriesViewModel = seriesViewModel
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun TopRatedSeriesSection(
    modifier: Modifier = Modifier,
    navController: NavController,
    seriesViewModel: SeriesViewModel,
    selectedGenderIndexState: MutableState<Int>
) {
    Column(
        modifier = modifier
    ) {
        TitleSection(
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.padding_24))
                .weight(0.1f, true),
            title = stringResource(id = R.string.common_top_rated)
        )
        Crossfade(targetState = seriesViewModel.seriesTopRatedState) { seriesTopRatedState ->
            when (seriesTopRatedState.value) {
                is Results.Loading ->
                    PlaceholderCardHorizontal(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.7f, true)
                    )
                is Results.Error ->
                    PlaceholderImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.7f, true),
                        imageId = R.drawable.cat_error
                    )
                is Results.Success -> {
                    val seriesList = (seriesTopRatedState.value.data as Pager<Serie>).results
                    if (seriesList.isEmpty()) {
                        PlaceholderImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.7f, true),
                            imageId = R.drawable.folder_empty
                        )
                    } else {
                        HorizontalPager(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.7f, true),
                            count = seriesList.size
                        ) { page ->
                            CardHorizontalSerie(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = dimensionResource(id = R.dimen.padding_24),
                                        vertical = dimensionResource(id = R.dimen.padding_16)
                                    ),
                                navController = navController,
                                serie = seriesList[page],
                                page = page,
                                pagerScope = this
                            )
                        }
                    }
                }
            }
        }
        ChipsGendersList(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f, true),
            namesList = App.seriesGendersList,
            disabledAll = seriesViewModel.seriesPopularState.value is Results.Loading || seriesViewModel.seriesPopularState.value is Results.Error,
            selectedPositionState = selectedGenderIndexState
        ) { index, genderId ->

        }
    }
}


@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PopularSeriesSection(
    modifier: Modifier,
    navController: NavController,
    seriesViewModel: SeriesViewModel
) {
    val pagerState = rememberPagerState()
    Column(
        modifier = modifier
    ) {
        TitleSection(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(id = R.dimen.padding_24))
                .weight(0.1f, true),
            title = stringResource(id = R.string.common_trending_now)
        )
        Crossfade(targetState = seriesViewModel.seriesPopularState) { seriesPopularState ->
            when (seriesPopularState.value) {
                is Results.Loading ->
                    PlaceholderCardVertical(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.9f, true)
                    )
                is Results.Error ->
                    PlaceholderImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.9f, true),
                        imageId = R.drawable.cat_error
                    )
                is Results.Success -> {
                    val seriesList = (seriesPopularState.value.data as Pager<Serie>).results
                    if (seriesList.isEmpty()) {
                        PlaceholderImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.9f, true),
                            imageId = R.drawable.folder_empty
                        )
                    } else {
                        if (seriesList.size > 2) {
                            LaunchedEffect(true) {
                                pagerState.scrollToPage(1)
                            }
                        }
                        HorizontalPager(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.9f, true),
                            count = seriesList.size,
                            contentPadding = PaddingValues(
                                horizontal = 75.dp
                            ),
                            state = pagerState
                        ) { page ->
                            CardVerticalSerie(
                                navController = navController,
                                serie = seriesList[page],
                                page = page,
                                pagerScope = this
                            )
                        }
                    }
                }
            }
        }
    }
}