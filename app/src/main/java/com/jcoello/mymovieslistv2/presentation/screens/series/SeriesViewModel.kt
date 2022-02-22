package com.jcoello.mymovieslistv2.presentation.screens.series

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jcoello.domain.model.Serie
import com.jcoello.domain.useCase.GetTopSeriesUseCase
import com.jcoello.domain.useCase.GetPopularSeriesUseCase
import com.jcoello.domain.util.Pager
import com.jcoello.domain.util.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(
    private val getTopSeriesUseCase: GetTopSeriesUseCase,
    private val getPopularSeriesUseCase: GetPopularSeriesUseCase
) : ViewModel() {

    private val _seriesTopRatedState = mutableStateOf<Results<Pager<Serie>>>(Results.Loading())
    val seriesTopRatedState: MutableState<Results<Pager<Serie>>> get() = _seriesTopRatedState

    private val _seriesPopularState = mutableStateOf<Results<Pager<Serie>>>(Results.Loading())
    val seriesPopularState: MutableState<Results<Pager<Serie>>> get() = _seriesPopularState

    init {
        getTopSeries()
        getPopularSeries()
    }

    private fun getTopSeries(){
        viewModelScope.launch {
            _seriesTopRatedState.value = getTopSeriesUseCase()
        }
    }

    private fun getPopularSeries(){
        viewModelScope.launch {
            _seriesPopularState.value = getPopularSeriesUseCase()
        }
    }

}