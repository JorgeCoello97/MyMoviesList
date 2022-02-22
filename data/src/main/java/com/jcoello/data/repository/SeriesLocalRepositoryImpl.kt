package com.jcoello.data.repository

import com.jcoello.data.mapper.SerieFavoriteMapper
import com.jcoello.data.repository.dataSource.local.SeriesLocalDataSource
import com.jcoello.domain.model.Serie
import com.jcoello.domain.repository.local.SeriesLocalRepository

class SeriesLocalRepositoryImpl(
    private val seriesLocalDataSource: SeriesLocalDataSource
) : SeriesLocalRepository {

    override fun getAllSeriesFavorites(): List<Serie> {
        val results = mutableListOf<Serie>()
        seriesLocalDataSource.getAllSerieFavorites().forEach { serieFavoriteEntity ->
            results.add(SerieFavoriteMapper.Local.toModel(serieFavoriteEntity = serieFavoriteEntity))
        }
        return results
    }

    override fun findSerieFavorite(serieId: Int): List<Serie> {
        val results = mutableListOf<Serie>()
        seriesLocalDataSource.findSerieFavorite(serieId = serieId).forEach { serieFavoriteEntity ->
            results.add(SerieFavoriteMapper.Local.toModel(serieFavoriteEntity = serieFavoriteEntity))
        }
        return results
    }

    override fun insertSerieFavorite(serie: Serie) {
        seriesLocalDataSource.insertSerieFavorite(SerieFavoriteMapper.Local.toDto(serie = serie))
    }

    override fun deleteSerieFavorite(serie: Serie) {
        seriesLocalDataSource.deleteSerieFavorite(SerieFavoriteMapper.Local.toDto(serie = serie))
    }

}