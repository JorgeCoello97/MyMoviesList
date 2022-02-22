package com.jcoello.domain.model

import java.io.Serializable

data class Movie(
    val id: Int,
    val adult: Boolean,
    val gendersIds: Array<Int>,
    val title: String,
    val overview: String,
    val posterPath: String,
    val bannerPath: String,
    val voteAverage: Float,
    val releaseDate: String
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Movie

        if (id != other.id) return false
        if (adult != other.adult) return false
        if (!gendersIds.contentEquals(other.gendersIds)) return false
        if (title != other.title) return false
        if (overview != other.overview) return false
        if (posterPath != other.posterPath) return false
        if (bannerPath != other.bannerPath) return false
        if (voteAverage != other.voteAverage) return false
        if (releaseDate != other.releaseDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + adult.hashCode()
        result = 31 * result + gendersIds.contentHashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + overview.hashCode()
        result = 31 * result + posterPath.hashCode()
        result = 31 * result + bannerPath.hashCode()
        result = 31 * result + voteAverage.hashCode()
        result = 31 * result + releaseDate.hashCode()
        return result
    }
}
