package com.jcoello.data.mapper

import com.jcoello.data.dto.SerieDto
import com.jcoello.domain.model.Serie
import com.jcoello.domain.util.Pager

class SerieMapper {
    class Local {
        companion object {

        }
    }

    class Remote {
        companion object {
            fun toModel(serieDto: SerieDto): Serie {
                return Serie(
                    id = serieDto.id,
                    gendersIds = serieDto.gendersIds ?: arrayOf(),
                    title = serieDto.title ?: "",
                    overview = serieDto.overview ?: "",
                    posterPath = serieDto.posterPath ?: "",
                    bannerPath = serieDto.bannerPath ?: "",
                    voteAverage = serieDto.voteAverage ?: 0f,
                    releaseDate = serieDto.releaseDate ?: ""
                )
            }

            fun toDto(serie: Serie): SerieDto {
                return SerieDto(
                    id = serie.id,
                    gendersIds = serie.gendersIds,
                    title = serie.title,
                    overview = serie.overview,
                    posterPath = serie.posterPath,
                    bannerPath = serie.bannerPath,
                    voteAverage = serie.voteAverage,
                    releaseDate = serie.releaseDate
                )
            }

            fun toPagerModel(pagerDto: Pager<SerieDto>): Pager<Serie> {
                val pagerModel = Pager<Serie>()
                pagerModel.page = pagerDto.page

                val listAux = mutableListOf<Serie>()
                pagerDto.results.forEach { serieDto ->
                    val serie = toModel(serieDto = serieDto)
                    listAux.add(serie)
                }
                pagerModel.results = listAux

                return pagerModel
            }
        }
    }
}