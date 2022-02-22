package com.jcoello.domain.useCase

import com.jcoello.domain.repository.remote.MoviesRemoteRepository

class GetPopularMoviesUseCase(private val moviesRemoteRepository: MoviesRemoteRepository) {
    suspend operator fun invoke() = moviesRemoteRepository.getPopularMovies()
}