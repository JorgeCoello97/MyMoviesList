package com.jcoello.data.mapper

import com.jcoello.data.database.entities.MovieFavoriteEntity
import com.jcoello.domain.model.Movie

class MovieFavoriteMapper {
    class Local {
        companion object {
            fun toModel(movieFavoriteEntity: MovieFavoriteEntity): Movie {
                return Movie(
                    id = movieFavoriteEntity.id,
                    adult = movieFavoriteEntity.adult ?: false,
                    gendersIds = movieFavoriteEntity.gendersIds.toTypedArray(),
                    title = movieFavoriteEntity.title ?: "",
                    overview = movieFavoriteEntity.overview ?: "",
                    posterPath = movieFavoriteEntity.posterPath ?: "",
                    bannerPath = movieFavoriteEntity.bannerPath ?: "",
                    voteAverage = movieFavoriteEntity.voteAverage ?: 0f,
                    releaseDate = movieFavoriteEntity.releaseDate ?: ""
                )
            }

            fun toDto(movie: Movie): MovieFavoriteEntity {
                return MovieFavoriteEntity(
                    id = movie.id,
                    adult = movie.adult,
                    gendersIds = movie.gendersIds.toMutableList(),
                    title = movie.title,
                    overview = movie.overview,
                    posterPath = movie.posterPath,
                    bannerPath = movie.bannerPath,
                    voteAverage = movie.voteAverage,
                    releaseDate = movie.releaseDate
                )
            }
        }
    }

    class Remote {
        companion object {

        }
    }
}