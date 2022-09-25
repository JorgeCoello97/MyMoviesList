package com.jcoello.data.mapper

import com.jcoello.data.dto.SerieDto
import com.jcoello.domain.model.Serie
import com.jcoello.domain.util.Pager

fun SerieDto.toSerie(): Serie {
    return Serie(
        id = id,
        gendersIds = gendersIds ?: arrayOf(),
        title = title ?: "",
        overview = overview ?: "",
        posterPath = posterPath ?: "",
        bannerPath = bannerPath ?: "",
        voteAverage = voteAverage ?: 0f,
        releaseDate = releaseDate ?: ""
    )
}

fun Serie.toSerieDto(): SerieDto {
    return SerieDto(
        id = id,
        gendersIds = gendersIds,
        title = title,
        overview = overview,
        posterPath = posterPath,
        bannerPath = bannerPath,
        voteAverage = voteAverage,
        releaseDate = releaseDate
    )
}


class SerieMapperUtils {
    companion object {
        fun toPagerModel(pagerDto: Pager<SerieDto>): Pager<Serie> {
            val pagerModel = Pager<Serie>()
            pagerModel.page = pagerDto.page

            val listAux = mutableListOf<Serie>()
            pagerDto.results.forEach { serieDto ->
                val serie = serieDto.toSerie()
                listAux.add(serie)
            }
            pagerModel.results = listAux

            return pagerModel
        }
    }
}