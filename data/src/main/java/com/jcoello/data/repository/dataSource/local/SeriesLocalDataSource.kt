package com.jcoello.data.repository.dataSource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.jcoello.data.database.entities.SerieFavoriteEntity

@Dao
interface SeriesLocalDataSource {

    @Query("SELECT * FROM serie_favorite")
    fun getAllSerieFavorites(): List<SerieFavoriteEntity>

    @Query("SELECT * FROM serie_favorite WHERE id LIKE :serieId")
    fun findSerieFavorite(serieId: Int): List<SerieFavoriteEntity>

    @Insert
    fun insertSerieFavorite(seriesEntity: SerieFavoriteEntity)

    @Delete
    fun deleteSerieFavorite(seriesEntity: SerieFavoriteEntity)
}