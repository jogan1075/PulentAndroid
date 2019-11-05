package com.jmc.pulentandroid.domain.usercase

import com.jmc.pulentandroid.domain.model.Artist
import com.jmc.pulentandroid.domain.model.request.SearchArtistsRequest
import com.jmc.pulentandroid.domain.repository.LocalRepository
import com.jmc.pulentandroid.domain.repository.RemoteRepository
import com.jmc.pulentandroid.utils.base.coroutines.ResultUseCase
import kotlinx.coroutines.Dispatchers

/**
 * Created by Jmunoz on 2019-10-31.
 */


open class SearchArtistsUseCase(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) : ResultUseCase<SearchArtistsRequest, List<Artist>>(
    backgroundContext = Dispatchers.IO,
    foregroundContext = Dispatchers.Main
) {
    override suspend fun executeOnBackground(params: SearchArtistsRequest): List<Artist>? {

        /* Get cache results from local cache */
        val cacheResults = localRepository.searchArtists(term = params.query)

        /* If there are cached results, return */
        if (cacheResults.isNotEmpty()) return cacheResults

        /* Get results from remote api */
        val remoteResults = remoteRepository.searchArtists(term = params.query)

        /* Save results to local cache */
        localRepository.saveArtistsSearch(artists = remoteResults, queryString = params.query)

        /* Return remote results */
        return remoteResults
    }
}