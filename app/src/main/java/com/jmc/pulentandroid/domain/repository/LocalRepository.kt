package com.jmc.pulentandroid.domain.repository

import com.jmc.pulentandroid.domain.model.Album
import com.jmc.pulentandroid.domain.model.Artist
import com.jmc.pulentandroid.domain.model.Track

/**
 * Created by Jmunoz on 2019-11-02.
 */


interface LocalRepository {
    suspend fun searchArtists(term: String): List<Artist>

    suspend fun searchAlbums(artistId: Long): List<Album>
    suspend fun searchTracks(albumId: Long): List<Track>

    suspend fun saveArtistsSearch(artists: List<Artist>, queryString: String)
    suspend fun saveAlbumsSearch(albums: List<Album>)
    suspend fun saveTracksSearch(tracks: List<Track>)

}
