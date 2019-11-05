package com.jmc.pulentandroid.utils

import com.jmc.pulentandroid.R
import com.jmc.pulentandroid.data.entity.api.AlbumEntity
import com.jmc.pulentandroid.domain.model.Artist
import com.jmc.pulentandroid.data.entity.api.ArtistEntity
import com.jmc.pulentandroid.data.entity.api.TrackEntity
import com.jmc.pulentandroid.data.entity.database.AlbumEntityBD
import com.jmc.pulentandroid.data.entity.database.ArtistEntityBD
import com.jmc.pulentandroid.data.entity.database.TrackEntityBD
import com.jmc.pulentandroid.domain.model.Album
import com.jmc.pulentandroid.domain.model.Track
import com.jmc.pulentandroid.presentation.modelView.TrackItem

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

fun TrackEntity.toTrack() = Track(
    artistId = artistId,
    collectionId = collectionId,
    trackId = trackId,
    artistName = artistName,
    collectionName = collectionName,
    trackName = trackName,
    collectionCensoredName = collectionCensoredName,
    trackCensoredName = trackCensoredName,
    artistViewUrl = artistViewUrl,
    collectionViewUrl = collectionViewUrl,
    trackViewUrl = trackViewUrl,
    previewUrl = previewUrl,
    collectionPrice = collectionPrice,
    trackPrice = trackPrice,
    releaseDate = releaseDate,
    isCollectionExplicit = collectionExplicitness != "notExplicit",
    isTrackExplicit = trackExplicitness != "notExplicit",
    discCount = discCount,
    discNumber = discNumber,
    trackCount = trackCount,
    trackNumber = trackNumber,
    trackTimeMillis = trackTimeMillis,
    country = country,
    currency = currency,
    primaryGenreName = primaryGenreName,
    isStreamable = isStreamable,
    isDownloading = false,
    isPlaying = false,
    isPaused = false,
    isDownloaded = false,
    isMusic = previewUrl.endsWith(".m4a"),
    isVideo = previewUrl.endsWith(".m4v")
)

fun ArtistEntityBD.toArtist() = Artist(
    artistId = artistId,
    artistName = artistName,
    artistLinkUrl = artistLinkUrl,
    primaryGenreName = primaryGenreName,
    primaryGenreId = primaryGenreId,
    isLiked = (like == 1)
)

fun AlbumEntityBD.toAlbum() = Album(
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
    isExplicit = (isExplicit == 1),
    trackCount = trackCount,
    copyright = copyright,
    country = country,
    currency = currency,
    releaseDate = java.util.Date(releaseDate),
    primaryGenreName = primaryGenreName
)

fun TrackEntityBD.toTrack() = Track(
    artistId = artistId,
    collectionId = collectionId,
    trackId = trackId,
    artistName = artistName,
    collectionName = collectionName,
    trackName = trackName,
    collectionCensoredName = collectionCensoredName,
    trackCensoredName = trackCensoredName,
    artistViewUrl = artistViewUrl,
    collectionViewUrl = collectionViewUrl,
    trackViewUrl = trackViewUrl,
    previewUrl = previewUrl,
    collectionPrice = collectionPrice,
    trackPrice = trackPrice,
    releaseDate = releaseDate,
    isCollectionExplicit = (isCollectionExplicit == 1),
    isTrackExplicit = (isTrackExplicit == 1),
    discCount = discCount,
    discNumber = discNumber,
    trackCount = trackCount,
    trackNumber = trackNumber,
    trackTimeMillis = trackTimeMillis,
    country = country,
    currency = currency,
    primaryGenreName = primaryGenreName,
    isStreamable = (isStreamable == 1),
    isDownloading = false,
    isPlaying = false,
    isPaused = false,
    isDownloaded = (isDownloaded == 1),
    isMusic = previewUrl.endsWith(".m4a"),
    isVideo = previewUrl.endsWith(".m4v")
)

/* from domain to data layer */

fun Artist.toArtistEntity(queryString: String) = ArtistEntityBD(
    artistId = artistId,
    artistName = artistName,
    artistLinkUrl = artistLinkUrl,
    primaryGenreName = primaryGenreName,
    primaryGenreId = primaryGenreId,
    like = if (isLiked) 1 else 0,
    queryString = queryString
)

fun Album.toAlbumEntity() = AlbumEntityBD(
    collectionId = collectionId,
    artistId = artistId,
    artistName = artistName,
    collectionName = collectionName,
    collectionCensoredName = collectionCensoredName,
    artistViewUrl = artistViewUrl,
    collectionViewUrl = collectionViewUrl,
    artworkUrl30 = artworkUrl30,
    artworkUrl60 = artworkUrl60,
    artworkUrl100 = artworkUrl100,
    collectionPrice = collectionPrice,
    isExplicit = if (isExplicit) 1 else 0,
    trackCount = trackCount,
    copyright = copyright,
    country = country,
    currency = currency,
    releaseDate = releaseDate.time,
    primaryGenreName = primaryGenreName
)

fun Track.toTrackEntity() = TrackEntityBD(
    artistId = artistId,
    collectionId = collectionId,
    trackId = trackId,
    artistName = artistName,
    collectionName = collectionName,
    trackName = trackName,
    collectionCensoredName = collectionCensoredName,
    trackCensoredName = trackCensoredName,
    artistViewUrl = artistViewUrl,
    collectionViewUrl = collectionViewUrl,
    trackViewUrl = trackViewUrl,
    previewUrl = previewUrl,
    collectionPrice = collectionPrice,
    trackPrice = trackPrice,
    releaseDate = releaseDate,
    isCollectionExplicit = if (isCollectionExplicit) 1 else 0,
    isTrackExplicit = if (isTrackExplicit) 1 else 0,
    discCount = discCount,
    discNumber = discNumber,
    trackCount = trackCount,
    trackNumber = trackNumber,
    trackTimeMillis = trackTimeMillis,
    country = country,
    currency = currency,
    primaryGenreName = primaryGenreName,
    isStreamable = if (isStreamable) 1 else 0,
    isDownloaded = if (isDownloaded) 1 else 0
)

/* from domain to presentation layer */

fun Track.toTrackItem() = TrackItem(
    artistId = artistId,
    collectionId = collectionId,
    trackId = trackId,
    artistName = artistName,
    collectionName = collectionName,
    trackName = trackName,
    collectionCensoredName = collectionCensoredName,
    trackCensoredName = trackCensoredName,
    artistViewUrl = artistViewUrl,
    collectionViewUrl = collectionViewUrl,
    trackViewUrl = trackViewUrl,
    previewUrl = previewUrl,
    collectionPrice = collectionPrice,
    trackPrice = trackPrice,
    releaseDate = releaseDate,
    isCollectionExplicit = isCollectionExplicit,
    isTrackExplicit = isTrackExplicit,
    discCount = discCount,
    discNumber = discNumber,
    trackCount = trackCount,
    trackNumber = trackNumber,
    trackTimeMillis = trackTimeMillis,
    country = country,
    currency = currency,
    primaryGenreName = primaryGenreName,
    isStreamable = isStreamable,
    isDownloading = isDownloading,
    isPlaying = isPlaying,
    isPaused = isPaused,
    isDownloaded = isDownloaded,
    isMusic = isMusic,
    isVideo = isVideo,
    /* Presentation fields */
    nameIconResource = if (isTrackExplicit) R.drawable.ic_explicit else 0,
    timeMillisString = trackTimeMillis.formatInterval()
)

/* from presentation to domain layer */

fun TrackItem.toTrack() = Track(
    artistId = artistId,
    collectionId = collectionId,
    trackId = trackId,
    artistName = artistName,
    collectionName = collectionName,
    trackName = trackName,
    collectionCensoredName = collectionCensoredName,
    trackCensoredName = trackCensoredName,
    artistViewUrl = artistViewUrl,
    collectionViewUrl = collectionViewUrl,
    trackViewUrl = trackViewUrl,
    previewUrl = previewUrl,
    collectionPrice = collectionPrice,
    trackPrice = trackPrice,
    releaseDate = releaseDate,
    isCollectionExplicit = isCollectionExplicit,
    isTrackExplicit = isTrackExplicit,
    discCount = discCount,
    discNumber = discNumber,
    trackCount = trackCount,
    trackNumber = trackNumber,
    trackTimeMillis = trackTimeMillis,
    country = country,
    currency = currency,
    primaryGenreName = primaryGenreName,
    isStreamable = isStreamable,
    isDownloading = isDownloading,
    isPlaying = isPlaying,
    isPaused = isPaused,
    isDownloaded = isDownloaded,
    isMusic = isMusic,
    isVideo = isVideo
)