package com.jmc.pulentandroid.data.datasource.remote

import com.jmc.pulentandroid.domain.model.Artist
import com.jmc.pulentandroid.domain.repository.RemoteRepository
import com.jmc.pulentandroid.data.datasource.remote.api.iTunesSearchApi
import com.jmc.pulentandroid.utils.await
import com.jmc.pulentandroid.utils.toArtist


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

}