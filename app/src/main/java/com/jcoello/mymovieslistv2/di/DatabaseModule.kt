package com.jcoello.mymovieslistv2.di

import android.content.Context
import androidx.room.Room
import com.jcoello.data.database.AppDatabase
import com.jcoello.data.repository.dataSource.local.MoviesLocalDataSource
import com.jcoello.data.repository.dataSource.local.SeriesLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "my_movieslist_db"
        ).build()
    }

    @Provides
    fun provideMoviesLocalDataSource(appDatabase: AppDatabase): MoviesLocalDataSource {
        return appDatabase.movieDao()
    }


    @Provides
    fun provideSeriesLocalDataSource(appDatabase: AppDatabase): SeriesLocalDataSource {
        return appDatabase.serieDao()
    }

}