package com.jmc.pulentandroid.domain.usercase

import com.jmc.pulentandroid.domain.model.Album
import com.jmc.pulentandroid.domain.model.request.LookupAlbumsRequest
import com.jmc.pulentandroid.domain.repository.RemoteRepository
import com.jmc.pulentandroid.utils.base.coroutines.ResultUseCase
import kotlinx.coroutines.Dispatchers

/**
 * Created by Jmunoz on 2019-10-31.
 */

open class SearchAlbumsUseCase(
    private val remoteRepository: RemoteRepository
) : ResultUseCase<LookupAlbumsRequest, List<Album>>(
    backgroundContext = Dispatchers.IO,
    foregroundContext = Dispatchers.Main
) {
    override suspend fun executeOnBackground(params: LookupAlbumsRequest): List<Album>? {

        /* Get results from remote api */
        val remoteResults = remoteRepository.searchAlbums(artistId = params.artistId)

        /* Return remote results */
        return remoteResults
    }
}