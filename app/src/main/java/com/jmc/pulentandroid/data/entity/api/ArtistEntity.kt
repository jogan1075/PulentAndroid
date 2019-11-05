package com.jmc.pulentandroid.data.entity.api

/**
 * Created by Jmunoz on 2019-10-31.
 */

data class ArtistEntity(
    val artistId: Long,
    val artistName: String,
    val artistLinkUrl: String?,
    val primaryGenreName: String?,
    val primaryGenreId: Long?
)