package com.jcoello.data.mapper

import com.jcoello.data.dto.MovieDto
import com.jcoello.domain.model.Movie
import com.jcoello.domain.util.Pager

class MovieMapper {
    class Local {
        companion object {

        }
    }
    class Remote {
        companion object {
            fun toModel(movieDto: MovieDto): Movie {
                return Movie(
                    id = movieDto.id,
                    adult = movieDto.adult ?: false,
                    gendersIds = movieDto.gendersIds ?: arrayOf(),
                    title = movieDto.title ?: "",
                    overview = movieDto.overview ?: "",
                    posterPath = movieDto.posterPath ?: "",
                    bannerPath = movieDto.bannerPath ?: "",
                    voteAverage = movieDto.voteAverage ?: 0f,
                    releaseDate = movieDto.releaseDate ?: ""
                )
            }

            fun toDto(movie: Movie): MovieDto {
                return MovieDto(
                    id = movie.id,
                    adult = movie.adult,
                    gendersIds = movie.gendersIds,
                    title = movie.title,
                    overview = movie.overview,
                    posterPath = movie.posterPath,
                    bannerPath = movie.bannerPath,
                    voteAverage = movie.voteAverage,
                    releaseDate = movie.releaseDate
                )
            }

            fun toPagerModel(pagerDto: Pager<MovieDto>):Pager<Movie> {
                val pagerModel = Pager<Movie>()
                pagerModel.page = pagerDto.page

                val listAux = mutableListOf<Movie>()
                pagerDto.results.forEach { movieDto ->
                    val movie = toModel(movieDto = movieDto)
                    listAux.add(movie)
                }
                pagerModel.results = listAux

                return pagerModel
            }
        }
    }
}