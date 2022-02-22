package com.jcoello.domain.useCase

import com.jcoello.domain.repository.remote.SeriesRemoteRepository

class GetTopSeriesUseCase(private val seriesRemoteRepository: SeriesRemoteRepository) {
    suspend operator fun invoke() = seriesRemoteRepository.getTopSeries()
}