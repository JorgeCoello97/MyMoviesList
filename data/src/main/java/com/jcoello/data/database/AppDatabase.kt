package com.jcoello.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jcoello.data.database.entities.MovieFavoriteEntity
import com.jcoello.data.database.entities.SerieFavoriteEntity
import com.jcoello.data.repository.dataSource.local.MoviesLocalDataSource
import com.jcoello.data.repository.dataSource.local.SeriesLocalDataSource
import com.jcoello.data.utils.GenderConverter

@Database(
    entities = [MovieFavoriteEntity::class, SerieFavoriteEntity::class],
    version = 1
)
@TypeConverters(GenderConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MoviesLocalDataSource

    abstract fun serieDao(): SeriesLocalDataSource

}