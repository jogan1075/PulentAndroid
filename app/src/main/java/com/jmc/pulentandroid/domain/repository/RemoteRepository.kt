package com.jmc.pulentandroid.domain.repository


import com.jmc.pulentandroid.domain.model.Album
import com.jmc.pulentandroid.domain.model.Artist


/**
 * Created by Jmunoz on 2019-10-31.
 */
interface RemoteRepository {
    suspend fun searchArtists(term: String): List<Artist>

    suspend fun searchAlbums(artistId: Long): List<Album>
}