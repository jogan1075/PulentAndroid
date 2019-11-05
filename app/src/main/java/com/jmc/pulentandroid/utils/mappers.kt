package com.jmc.pulentandroid.utils

import com.jmc.pulentandroid.data.entity.api.AlbumEntity
import com.jmc.pulentandroid.domain.model.Artist
import com.jmc.pulentandroid.data.entity.api.ArtistEntity
import com.jmc.pulentandroid.domain.model.Album

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

fun AlbumEntity.toAlbum() = Album(
    collectionId = collectionId,
    artistId = artistId,
    artistName = artistName,
    collectionName = collectionName,
    collectionCensoredName = collectionCensoredName,
    artistViewUrl = artistViewUrl,
    collectionViewUrl = collectionViewUrl,
    artworkUrl30 = artworkUrl30 ?: "",
    artworkUrl60 = artworkUrl60 ?: "",
    artworkUrl100 = artworkUrl100 ?: "",
    collectionPrice = collectionPrice,
    isExplicit = collectionExplicitness != "notExplicit",
    trackCount = trackCount,
    copyright = copyright,
    country = country,
    currency = currency,
    releaseDate = releaseDate.parseISO8601Date(),
    primaryGenreName = primaryGenreName
)
