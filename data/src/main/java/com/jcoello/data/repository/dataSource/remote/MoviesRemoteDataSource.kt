package com.jcoello.data.repository.dataSource.remote

import com.jcoello.data.dto.MovieDto
import com.jcoello.domain.util.Pager
import retrofit2.Response

interface MoviesRemoteDataSource {
    suspend fun getUpComingMovies(): Response<Pager<MovieDto>>
    suspend fun getPopularMovies(): Response<Pager<MovieDto>>
}