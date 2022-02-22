package com.jcoello.mymovieslistv2.di

import com.jcoello.domain.repository.remote.MoviesRemoteRepository
import com.jcoello.domain.repository.remote.SeriesRemoteRepository
import com.jcoello.domain.useCase.GetTopSeriesUseCase
import com.jcoello.domain.useCase.GetPopularMoviesUseCase
import com.jcoello.domain.useCase.GetPopularSeriesUseCase
import com.jcoello.domain.useCase.GetUpComingMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideGetUpComingMoviesUseCase(moviesRemoteRepository: MoviesRemoteRepository): GetUpComingMoviesUseCase {
        return GetUpComingMoviesUseCase(moviesRemoteRepository)
    }

    @Provides
    fun provideGetPopularMoviesUseCase(moviesRemoteRepository: MoviesRemoteRepository): GetPopularMoviesUseCase {
        return GetPopularMoviesUseCase(moviesRemoteRepository)
    }

    @Provides
    fun provideGetLatestSeriesUseCase(seriesRemoteRepository: SeriesRemoteRepository): GetTopSeriesUseCase {
        return GetTopSeriesUseCase(seriesRemoteRepository)
    }


    @Provides
    fun provideGetPopularSeriesUseCase(seriesRemoteRepository: SeriesRemoteRepository): GetPopularSeriesUseCase {
        return GetPopularSeriesUseCase(seriesRemoteRepository)
    }

}