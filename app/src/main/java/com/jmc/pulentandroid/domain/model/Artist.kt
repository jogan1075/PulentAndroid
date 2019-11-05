package com.jmc.pulentandroid.domain.model

/**
 * Created by Jmunoz on 2019-10-31.
 */

data class Artist(
    val artistId: Long,
    val artistName: String,
    val artistLinkUrl: String,
    val primaryGenreName: String,
    val primaryGenreId: Long,
    val isLiked: Boolean
) {
    override fun hashCode() = artistId.toInt()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Artist

        return (artistId == other.artistId)
    }
}