package com.jcoello.data.repository

import com.jcoello.data.mapper.MovieMapper
import com.jcoello.data.repository.dataSource.remote.MoviesRemoteDataSource
import com.jcoello.domain.model.Movie
import com.jcoello.domain.repository.remote.MoviesRemoteRepository
import com.jcoello.domain.util.Pager
import com.jcoello.domain.util.Results

class MovieRemoteRepositoryImpl(
    private val moviesRemoteDataSource: MoviesRemoteDataSource
) : MoviesRemoteRepository {

    override suspend fun getUpComingMovies(): Results<Pager<Movie>> {
        val response = moviesRemoteDataSource.getUpComingMovies()
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Results.Success(data = MovieMapper.Remote.toPagerModel(result))
            }
        }
        return Results.Error(message = response.message())
    }

    override suspend fun getPopularMovies(): Results<Pager<Movie>> {
        val response = moviesRemoteDataSource.getPopularMovies()
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Results.Success(data = MovieMapper.Remote.toPagerModel(result))
            }
        }
        return Results.Error(message = response.message())
    }

}