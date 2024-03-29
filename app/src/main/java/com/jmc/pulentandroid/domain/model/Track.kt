package com.jmc.pulentandroid.domain.model

/**
 * Created by Jmunoz on 2019-11-01.
 */

data class Track(
    val artistId: Long,
    val collectionId: Long,
    val trackId: Long,
    val artistName: String,
    val collectionName: String,
    val trackName: String,
    val collectionCensoredName: String,
    val trackCensoredName: String,
    val artistViewUrl: String,
    val collectionViewUrl: String,
    val trackViewUrl: String,
    val previewUrl: String,
    val collectionPrice: Double,
    val trackPrice: Double,
    val releaseDate: String,
    val isCollectionExplicit: Boolean,
    val isTrackExplicit: Boolean,
    val discCount: Int,
    val discNumber: Int,
    val trackCount: Int,
    val trackNumber: Int,
    val trackTimeMillis: Long,
    val country: String,
    val currency: String,
    val primaryGenreName: String,
    val isStreamable: Boolean,
    val isDownloading: Boolean,
    val isPlaying: Boolean,
    val isPaused: Boolean,
    val isDownloaded: Boolean,
    val isMusic: Boolean,
    val isVideo: Boolean
)