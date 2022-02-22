package com.jcoello.data.repository.dataSource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.jcoello.data.database.entities.MovieFavoriteEntity

@Dao
interface MoviesLocalDataSource {

    @Query("SELECT * FROM movie_favorite")
    fun getAllMoviesFavorites(): List<MovieFavoriteEntity>

    @Query("SELECT * FROM movie_favorite WHERE id LIKE :movieId")
    fun findMovieFavorite(movieId: Int): List<MovieFavoriteEntity>

    @Insert
    fun insertMovieFavorite(movieFavoriteEntity: MovieFavoriteEntity)

    @Delete
    fun deleteMovieFavorite(movieFavoriteEntity: MovieFavoriteEntity)
}