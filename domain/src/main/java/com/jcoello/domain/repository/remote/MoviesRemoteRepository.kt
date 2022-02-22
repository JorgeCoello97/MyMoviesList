package com.jcoello.domain.repository.remote

import com.jcoello.domain.model.Movie
import com.jcoello.domain.util.Pager
import com.jcoello.domain.util.Results

interface MoviesRemoteRepository {

    suspend fun getUpComingMovies(): Results<Pager<Movie>>

    suspend fun getPopularMovies(): Results<Pager<Movie>>

}