package com.jmc.pulentandroid.data.datasource.remote

import com.jmc.pulentandroid.domain.model.Artist
import com.jmc.pulentandroid.domain.repository.RemoteRepository
import com.jmc.pulentandroid.data.datasource.remote.api.iTunesSearchApi
import com.jmc.pulentandroid.domain.model.Album
import com.jmc.pulentandroid.domain.model.Track
import com.jmc.pulentandroid.utils.await
import com.jmc.pulentandroid.utils.toAlbum
import com.jmc.pulentandroid.utils.toArtist
import com.jmc.pulentandroid.utils.toTrack


/**
 * Created by Jmunoz on 2019-10-31.
 */

open class RemoteDataStore(
    private val iTunesSearchApi: iTunesSearchApi
) : RemoteRepository {
    override suspend fun searchArtists(term: String): List<Artist> {
        val response = iTunesSearchApi.searchArtists(term).await()!!

        return response.results.map { it.toArtist() }
    }

    override suspend fun searchAlbums(artistId: Long): List<Album> {
        val response = iTunesSearchApi.lookupAlbums(artistId).await()!!

        return response.results.drop(1).map { it.toAlbum() }
    }


    override suspend fun searchTracks(albumId: Long): List<Track> {
        val response = iTunesSearchApi.lookupTracks(albumId).await()!!

        return response.results.drop(1).map { it.toTrack() }
    }
}