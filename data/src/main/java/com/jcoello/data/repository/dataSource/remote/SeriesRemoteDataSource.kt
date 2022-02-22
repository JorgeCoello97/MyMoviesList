package com.jcoello.data.repository.dataSource.remote

import com.jcoello.data.dto.SerieDto
import com.jcoello.domain.model.Serie
import com.jcoello.domain.util.Pager
import retrofit2.Response

interface SeriesRemoteDataSource {
    suspend fun getTopSeries(): Response<Pager<SerieDto>>
    suspend fun getPopularSeries(): Response<Pager<SerieDto>>
}