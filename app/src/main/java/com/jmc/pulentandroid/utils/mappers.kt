package com.jmc.pulentandroid.utils

import com.jmc.pulentandroid.domain.model.Artist
import com.jmc.pulentandroid.data.entity.api.ArtistEntity

/**
 * Created by Jmunoz on 2019-10-31.
 */

/* from data to domain layer */

fun ArtistEntity.toArtist() = Artist(
    artistId = artistId,
    artistName = artistName,
    artistLinkUrl = artistLinkUrl ?: "",
    primaryGenreName = primaryGenreName ?: "",
    primaryGenreId = primaryGenreId ?: 0,
    isLiked = false
)
