package com.jmc.pulentandroid.domain.usercase

import com.jmc.pulentandroid.domain.model.Track
import com.jmc.pulentandroid.domain.model.request.LookupTracksRequest
import com.jmc.pulentandroid.domain.repository.RemoteRepository
import com.jmc.pulentandroid.utils.base.coroutines.ResultUseCase
import kotlinx.coroutines.Dispatchers

/**
 * Created by Jmunoz on 2019-11-01.
 */

open class SearchTracksUseCase(

    private val remoteRepository: RemoteRepository
) : ResultUseCase<LookupTracksRequest, List<Track>>(
    backgroundContext = Dispatchers.IO,
    foregroundContext = Dispatchers.Main
) {
    override suspend fun executeOnBackground(params: LookupTracksRequest): List<Track>? {
        /* Get results from remoteRepository api */
        val remoteResults = remoteRepository.searchTracks(albumId = params.albumId)

        /* Return remoteRepository results */
        return remoteResults
    }
}