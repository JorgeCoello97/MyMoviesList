package com.jcoello.mymovieslistv2.presentation.screens.movies

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jcoello.domain.model.Movie
import com.jcoello.domain.useCase.GetPopularMoviesUseCase
import com.jcoello.domain.useCase.GetUpComingMoviesUseCase
import com.jcoello.domain.util.Pager
import com.jcoello.domain.util.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getUpComingMoviesUseCase: GetUpComingMoviesUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    private val _moviesComingSoonState = mutableStateOf<Results<Pager<Movie>>>(Results.Loading())
    val moviesComingSoonState: MutableState<Results<Pager<Movie>>> get() = _moviesComingSoonState

    private val _moviesPopularState = mutableStateOf<Results<Pager<Movie>>>(Results.Loading())
    val moviesPopularState: MutableState<Results<Pager<Movie>>> get() = _moviesPopularState

    init {
        getUpComingMovies()
        getPopularMovies()
    }

    private fun getUpComingMovies(){
        viewModelScope.launch {
            _moviesComingSoonState.value = getUpComingMoviesUseCase()
        }
    }

    private fun getPopularMovies(){
        viewModelScope.launch {
            _moviesPopularState.value = getPopularMoviesUseCase()
        }
    }
}