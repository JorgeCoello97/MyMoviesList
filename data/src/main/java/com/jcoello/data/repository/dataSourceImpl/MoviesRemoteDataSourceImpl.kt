package com.jcoello.data.repository.dataSourceImpl

import com.jcoello.data.BuildConfig
import com.jcoello.data.api.MoviesApi
import com.jcoello.data.dto.MovieDto
import com.jcoello.data.repository.dataSource.remote.MoviesRemoteDataSource
import com.jcoello.domain.util.Pager
import retrofit2.Response
import java.util.*

class MoviesRemoteDataSourceImpl(private val moviesApi: MoviesApi) : MoviesRemoteDataSource {

    override suspend fun getUpComingMovies(): Response<Pager<MovieDto>> {
        return moviesApi.getUpComingMovies(
            apiKey = BuildConfig.API_KEY,
            language = Locale.getDefault().language,
            page = 1
        )
    }

    override suspend fun getPopularMovies(): Response<Pager<MovieDto>> {
        return moviesApi.getPopularMovies(
            apiKey = BuildConfig.API_KEY,
            language = Locale.getDefault().language,
            page = 1
        )
    }

}