package com.jcoello.mymovieslistv2.di

import com.jcoello.data.repository.MovieRemoteRepositoryImpl
import com.jcoello.data.repository.MoviesLocalRepositoryImpl
import com.jcoello.data.repository.SeriesLocalRepositoryImpl
import com.jcoello.data.repository.SeriesRemoteRepositoryImpl
import com.jcoello.data.repository.dataSource.local.MoviesLocalDataSource
import com.jcoello.data.repository.dataSource.local.SeriesLocalDataSource
import com.jcoello.data.repository.dataSource.remote.MoviesRemoteDataSource
import com.jcoello.data.repository.dataSource.remote.SeriesRemoteDataSource
import com.jcoello.domain.repository.local.MoviesLocalRepository
import com.jcoello.domain.repository.local.SeriesLocalRepository
import com.jcoello.domain.repository.remote.MoviesRemoteRepository
import com.jcoello.domain.repository.remote.SeriesRemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideMoviesRemoteRepository(
        moviesRemoteDataSource: MoviesRemoteDataSource
    ): MoviesRemoteRepository {
        return MovieRemoteRepositoryImpl(moviesRemoteDataSource)
    }

    @Provides
    fun provideSeriesRemoteRepository(
        seriesRemoteDataSource: SeriesRemoteDataSource
    ): SeriesRemoteRepository {
        return SeriesRemoteRepositoryImpl(seriesRemoteDataSource)
    }

    @Provides
    fun provideMoviesLocalRepository(
        moviesLocalDataSource: MoviesLocalDataSource
    ): MoviesLocalRepository {
        return MoviesLocalRepositoryImpl(moviesLocalDataSource)
    }

    @Provides
    fun provideSeriesLocalRepository(
        seriesLocalDataSource: SeriesLocalDataSource
    ): SeriesLocalRepository{
        return SeriesLocalRepositoryImpl(seriesLocalDataSource)
    }

}