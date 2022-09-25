package com.jcoello.data.mapper

import com.jcoello.data.dto.MovieDto
import com.jcoello.domain.model.Movie
import com.jcoello.domain.util.Pager

fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id,
        adult = adult ?: false,
        gendersIds = gendersIds ?: arrayOf(),
        title = title ?: "",
        overview = overview ?: "",
        posterPath = posterPath ?: "",
        bannerPath = bannerPath ?: "",
        voteAverage = voteAverage ?: 0f,
        releaseDate = releaseDate ?: ""
    )
}

fun Movie.toMovieDto(): MovieDto {
    return MovieDto(
        id = id,
        adult = adult,
        gendersIds = gendersIds,
        title = title,
        overview = overview,
        posterPath = posterPath,
        bannerPath = bannerPath,
        voteAverage = voteAverage,
        releaseDate = releaseDate
    )
}



class MovieMapperUtils {
    companion object {
        fun toPagerModel(pagerDto: Pager<MovieDto>):Pager<Movie> {
            val pagerModel = Pager<Movie>()
            pagerModel.page = pagerDto.page

            val listAux = mutableListOf<Movie>()
            pagerDto.results.forEach { movieDto ->
                val movie = movieDto.toMovie()
                listAux.add(movie)
            }
            pagerModel.results = listAux

            return pagerModel
        }
    }
}