package com.jcoello.data.repository

import com.jcoello.data.mapper.SerieMapper
import com.jcoello.data.repository.dataSource.remote.SeriesRemoteDataSource
import com.jcoello.domain.model.Serie
import com.jcoello.domain.repository.remote.SeriesRemoteRepository
import com.jcoello.domain.util.Pager
import com.jcoello.domain.util.Results

class SeriesRemoteRepositoryImpl(
    private val seriesRemoteDataSource: SeriesRemoteDataSource
) : SeriesRemoteRepository {

    override suspend fun getTopSeries(): Results<Pager<Serie>> {
        val response = seriesRemoteDataSource.getTopSeries()
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Results.Success(data = SerieMapper.Remote.toPagerModel(result))
            }
        }
        return Results.Error(message = response.message())
    }

    override suspend fun getPopularSeries(): Results<Pager<Serie>> {
        val response = seriesRemoteDataSource.getPopularSeries()
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Results.Success(data = SerieMapper.Remote.toPagerModel(result))
            }
        }
        return Results.Error(message = response.message())
    }
}