package com.jmc.pulentandroid.domain.usercase

import com.jmc.pulentandroid.domain.model.Album
import com.jmc.pulentandroid.domain.model.request.LookupAlbumsRequest
import com.jmc.pulentandroid.domain.repository.LocalRepository
import com.jmc.pulentandroid.domain.repository.RemoteRepository
import com.jmc.pulentandroid.utils.base.coroutines.ResultUseCase
import kotlinx.coroutines.Dispatchers

/**
 * Created by Jmunoz on 2019-10-31.
 */

open class SearchAlbumsUseCase(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) : ResultUseCase<LookupAlbumsRequest, List<Album>>(
    backgroundContext = Dispatchers.IO,
    foregroundContext = Dispatchers.Main
) {
    override suspend fun executeOnBackground(params: LookupAlbumsRequest): List<Album>? {
        /* Get cache results from local cache */
        val cacheResults = localRepository.searchAlbums(artistId = params.artistId)

        /* If there are cached results, return */
        if (cacheResults.isNotEmpty()) return cacheResults

        /* Get results from remote api */
        val remoteResults = remoteRepository.searchAlbums(artistId = params.artistId)

        /* Save results to local cache */
        localRepository.saveAlbumsSearch(albums = remoteResults)

        /* Return remote results */
        return remoteResults
    }
}