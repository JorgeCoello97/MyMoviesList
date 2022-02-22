package com.jcoello.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_favorite")
data class MovieFavoriteEntity(

    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "adult")
    val adult: Boolean?,

    @ColumnInfo(name = "genre_ids")
    val gendersIds: MutableList<Int>,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "overview")
    val overview: String?,

    @ColumnInfo(name = "poster_path")
    val posterPath: String?,

    @ColumnInfo(name = "backdrop_path")
    val bannerPath: String?,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Float?,

    @ColumnInfo(name = "release_date")
    val releaseDate: String?

)