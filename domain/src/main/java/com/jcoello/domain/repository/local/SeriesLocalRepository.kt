package com.jcoello.domain.repository.local

import com.jcoello.domain.model.Serie

interface SeriesLocalRepository {

    fun getAllSeriesFavorites(): List<Serie>

    fun findSerieFavorite(serieId: Int): List<Serie>

    fun insertSerieFavorite(serie: Serie)

    fun deleteSerieFavorite(serie: Serie)
}