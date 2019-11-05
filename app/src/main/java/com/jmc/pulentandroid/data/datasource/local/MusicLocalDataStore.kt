package com.jmc.pulentandroid.data.datasource.local

import com.jmc.pulentandroid.data.datasource.local.database.MusicDatabase
import com.jmc.pulentandroid.domain.model.Album
import com.jmc.pulentandroid.domain.model.Artist
import com.jmc.pulentandroid.domain.model.Track
import com.jmc.pulentandroid.domain.repository.LocalRepository
import com.jmc.pulentandroid.utils.*

/**
 * Created by Jmunoz on 2019-11-02.
 */

open class MusicLocalDataStore(
    private val musicDatabase: MusicDatabase
) : LocalRepository {
    override suspend fun searchArtists(term: String): List<Artist> {
        return musicDatabase.artists.searchArtists(term = term.normalize()).map { it.toArtist() }
    }


    override suspend fun searchAlbums(artistId: Long): List<Album> {
        return musicDatabase.albums.searchAlbums(artistId = artistId).map { it.toAlbum() }
    }

    override suspend fun searchTracks(albumId: Long): List<Track> {
        return musicDatabase.tracks.searchTracks(albumId = albumId).map { it.toTrack() }
    }

    override suspend fun saveArtistsSearch(artists: List<Artist>, queryString: String) {
        val array = artists.map { it.toArtistEntity(queryString) }.toTypedArray()

        musicDatabase.artists.saveArtistsSearch(artists = *array)
    }

    override suspend fun saveAlbumsSearch(albums: List<Album>) {
        val array = albums.map { it.toAlbumEntity() }.toTypedArray()

        musicDatabase.albums.saveAlbumsSearch(albums = *array)
    }

    override suspend fun saveTracksSearch(tracks: List<Track>) {
        val array = tracks.map { it.toTrackEntity() }.toTypedArray()

        musicDatabase.tracks.saveTracksSearch(tracks = *array)
    }

}
