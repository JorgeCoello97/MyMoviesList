package com.jcoello.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "serie_favorite")
data class SerieFavoriteEntity(

    @PrimaryKey
    val id: Int,

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

    @ColumnInfo(name = "first_air_date")
    val releaseDate: String?

)