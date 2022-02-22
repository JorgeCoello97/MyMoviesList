package com.jcoello.domain.repository.local

import com.jcoello.domain.model.Movie

interface MoviesLocalRepository {

    fun getAllMoviesFavorites(): List<Movie>

    fun findMovieFavorite(movieId: Int): List<Movie>

    fun insertMovieFavorite(movie: Movie)

    fun deleteMovieFavorite(movie: Movie)

}