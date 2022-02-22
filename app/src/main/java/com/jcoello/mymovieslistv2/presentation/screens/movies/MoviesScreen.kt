package com.jcoello.mymovieslistv2.presentation.screens.movies

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
import com.jcoello.domain.model.Movie
import com.jcoello.domain.util.Pager
import com.jcoello.domain.util.Results
import com.jcoello.mymovieslistv2.App
import com.jcoello.mymovieslistv2.R
import com.jcoello.mymovieslistv2.presentation.components.*

@Composable
fun MoviesScreen(
    navController: NavController,
    selectedTabIndexState: MutableState<Int>
) {
    val moviesViewModel: MoviesViewModel = hiltViewModel()
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
        ComingSoonSection(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.3f),
            navController = navController,
            moviesViewModel = moviesViewModel,
            selectedGenderIndexState = selectedGenderIndexState
        )
        TrendingNowSection(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f),
            navController = navController,
            moviesViewModel = moviesViewModel
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun ComingSoonSection(
    modifier: Modifier = Modifier,
    navController: NavController,
    moviesViewModel: MoviesViewModel,
    selectedGenderIndexState: MutableState<Int>
) {
    Column(
        modifier = modifier
    ) {
        TitleSection(
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.padding_24))
                .weight(0.1f, true),
            title = stringResource(id = R.string.common_coming_soon)
        )
        Crossfade(targetState = moviesViewModel.moviesComingSoonState) { moviesComingSoonState ->
            when (moviesComingSoonState.value) {
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
                    val moviesList = (moviesComingSoonState.value.data as Pager<Movie>).results
                    if (moviesList.isEmpty()) {
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
                            count = moviesList.size
                        ) { page ->
                            CardHorizontalMovie(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = dimensionResource(id = R.dimen.padding_24),
                                        vertical = dimensionResource(id = R.dimen.padding_16)
                                    ),
                                navController = navController,
                                movie = moviesList[page],
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
            namesList = App.moviesGendersList,
            disabledAll = moviesViewModel.moviesPopularState.value is Results.Loading || moviesViewModel.moviesPopularState.value is Results.Error,
            selectedPositionState = selectedGenderIndexState
        ) { index, genderId ->

        }
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TrendingNowSection(
    modifier: Modifier,
    navController: NavController,
    moviesViewModel: MoviesViewModel
) {
    val pagerState = rememberPagerState()
    Column(
        modifier = modifier
    ) {
        TitleSection(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.padding_12))
                .padding(horizontal = dimensionResource(id = R.dimen.padding_24))
                .weight(0.1f, true),
            title = stringResource(id = R.string.common_trending_now)
        )
        Crossfade(targetState = moviesViewModel.moviesPopularState) { moviesPopularState ->
            when (moviesPopularState.value) {
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
                    val moviesList = (moviesPopularState.value.data as Pager<Movie>).results
                    if (moviesList.isEmpty()) {
                        PlaceholderImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.9f, true),
                            imageId = R.drawable.folder_empty
                        )
                    } else {
                        if (moviesList.size > 2) {
                            LaunchedEffect(true) {
                                pagerState.scrollToPage(1)
                            }
                        }
                        HorizontalPager(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.9f, true),
                            count = moviesList.size,
                            contentPadding = PaddingValues(
                                horizontal = 75.dp
                            ),
                            state = pagerState
                        ) { page ->
                            CardVerticalMovie(
                                navController = navController,
                                movie = moviesList[page],
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