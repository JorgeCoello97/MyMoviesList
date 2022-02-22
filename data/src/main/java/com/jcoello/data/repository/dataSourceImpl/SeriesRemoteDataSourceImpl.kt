package com.jcoello.data.repository.dataSourceImpl

import com.jcoello.data.BuildConfig
import com.jcoello.data.api.MoviesApi
import com.jcoello.data.dto.SerieDto
import com.jcoello.data.repository.dataSource.remote.SeriesRemoteDataSource
import com.jcoello.domain.util.Pager
import retrofit2.Response
import java.util.*

class SeriesRemoteDataSourceImpl(private val moviesApi: MoviesApi) : SeriesRemoteDataSource {
    override suspend fun getTopSeries(): Response<Pager<SerieDto>> {
        return moviesApi.getTopSeries(
            apiKey = BuildConfig.API_KEY,
            language = Locale.getDefault().language,
            page = 1
        )
    }

    override suspend fun getPopularSeries(): Response<Pager<SerieDto>> {
        return moviesApi.getPopularSeries(
            apiKey = BuildConfig.API_KEY,
            language = Locale.getDefault().language,
            page = 1
        )
    }
}