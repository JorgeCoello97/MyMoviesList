package com.jcoello.domain.repository.remote

import com.jcoello.domain.model.Serie
import com.jcoello.domain.util.Pager
import com.jcoello.domain.util.Results

interface SeriesRemoteRepository {

    suspend fun getTopSeries(): Results<Pager<Serie>>

    suspend fun getPopularSeries(): Results<Pager<Serie>>

}