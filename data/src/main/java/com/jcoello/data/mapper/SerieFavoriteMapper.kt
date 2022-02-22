package com.jcoello.data.mapper

import com.jcoello.data.database.entities.SerieFavoriteEntity
import com.jcoello.domain.model.Serie

class SerieFavoriteMapper {
    class Local {
        companion object {
            fun toModel(serieFavoriteEntity: SerieFavoriteEntity): Serie {
                return Serie(
                    id = serieFavoriteEntity.id,
                    gendersIds = serieFavoriteEntity.gendersIds.toTypedArray(),
                    title = serieFavoriteEntity.title ?: "",
                    overview = serieFavoriteEntity.overview ?: "",
                    posterPath = serieFavoriteEntity.posterPath ?: "",
                    bannerPath = serieFavoriteEntity.bannerPath ?: "",
                    voteAverage = serieFavoriteEntity.voteAverage ?: 0f,
                    releaseDate = serieFavoriteEntity.releaseDate ?: ""
                )
            }

            fun toDto(serie: Serie): SerieFavoriteEntity {
                return SerieFavoriteEntity(
                    id = serie.id,
                    gendersIds = serie.gendersIds.toMutableList(),
                    title = serie.title,
                    overview = serie.overview,
                    posterPath = serie.posterPath,
                    bannerPath = serie.bannerPath,
                    voteAverage = serie.voteAverage,
                    releaseDate = serie.releaseDate
                )
            }
        }
    }

    class Remote {
        companion object {

        }
    }
}