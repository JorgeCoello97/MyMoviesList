package com.jcoello.data.dto

import androidx.room.Ignore
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SerieDto(

    @SerializedName("id")
    val id: Int,

    @SerializedName("genre_ids")
    val gendersIds: Array<Int>? = arrayOf(),

    @SerializedName("name")
    val title: String?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("backdrop_path")
    val bannerPath: String?,

    @SerializedName("vote_average")
    val voteAverage: Float?,

    @SerializedName("first_air_date")
    val releaseDate: String?

) : Serializable {

    @Ignore
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SerieDto

        if (id != other.id) return false
        if (!gendersIds.contentEquals(other.gendersIds)) return false
        if (title != other.title) return false
        if (overview != other.overview) return false
        if (posterPath != other.posterPath) return false
        if (bannerPath != other.bannerPath) return false
        if (voteAverage != other.voteAverage) return false
        if (releaseDate != other.releaseDate) return false

        return true
    }

    @Ignore
    override fun hashCode(): Int {
        var result = id
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