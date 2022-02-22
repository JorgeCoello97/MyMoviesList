package com.jcoello.data.repository

import com.jcoello.data.mapper.MovieFavoriteMapper
import com.jcoello.data.repository.dataSource.local.MoviesLocalDataSource
import com.jcoello.domain.model.Movie
import com.jcoello.domain.repository.local.MoviesLocalRepository

class MoviesLocalRepositoryImpl(
    private val moviesLocalDataSource: MoviesLocalDataSource
) : MoviesLocalRepository {

    override fun getAllMoviesFavorites(): List<Movie> {
        val results = mutableListOf<Movie>()
        moviesLocalDataSource.getAllMoviesFavorites().forEach { movieFavoriteEntity ->
            results.add(MovieFavoriteMapper.Local.toModel(movieFavoriteEntity = movieFavoriteEntity))
        }
        return results
    }

    override fun findMovieFavorite(movieId: Int): List<Movie> {
        val results = mutableListOf<Movie>()
        moviesLocalDataSource.findMovieFavorite(movieId = movieId).forEach { movieFavoriteEntity ->
            results.add(MovieFavoriteMapper.Local.toModel(movieFavoriteEntity = movieFavoriteEntity))
        }
        return results
    }

    override fun insertMovieFavorite(movie: Movie) {
        moviesLocalDataSource.insertMovieFavorite(MovieFavoriteMapper.Local.toDto(movie = movie))
    }

    override fun deleteMovieFavorite(movie: Movie) {
        moviesLocalDataSource.deleteMovieFavorite(MovieFavoriteMapper.Local.toDto(movie = movie))
    }
}