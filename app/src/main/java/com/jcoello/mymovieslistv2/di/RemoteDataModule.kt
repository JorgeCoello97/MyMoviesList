package com.jcoello.mymovieslistv2.di

import com.jcoello.data.api.MoviesApi
import com.jcoello.data.repository.dataSource.remote.MoviesRemoteDataSource
import com.jcoello.data.repository.dataSource.remote.SeriesRemoteDataSource
import com.jcoello.data.repository.dataSourceImpl.MoviesRemoteDataSourceImpl
import com.jcoello.data.repository.dataSourceImpl.SeriesRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Provides
    fun provideMoviesRemoteDataSource(moviesApi: MoviesApi): MoviesRemoteDataSource {
        return MoviesRemoteDataSourceImpl(moviesApi)
    }

    @Provides
    fun provideSeriesRemoteDataSource(moviesApi: MoviesApi): SeriesRemoteDataSource {
        return SeriesRemoteDataSourceImpl(moviesApi)
    }

}