package com.jmc.pulentandroid.domain.usercase


import com.jmc.pulentandroid.data.datasource.local.database.MusicDatabase
import com.jmc.pulentandroid.domain.model.request.DownloadTrackRequest
import com.jmc.pulentandroid.domain.model.response.DownloadTrackResponse
import com.jmc.pulentandroid.domain.repository.StorageRepository
import com.jmc.pulentandroid.utils.base.coroutines.ResultUseCase
import kotlinx.coroutines.Dispatchers
import java.io.File

/**
 * Created by Jmunoz on 2019-11-01.
 */

open class DownloadTrackUseCase(
    private val musicDatabase: MusicDatabase,
    private val storageRepository: StorageRepository

) : ResultUseCase<DownloadTrackRequest, DownloadTrackResponse>(
    backgroundContext = Dispatchers.IO,
    foregroundContext = Dispatchers.Main
) {
    override suspend fun executeOnBackground(params: DownloadTrackRequest): DownloadTrackResponse? {
        val track = params.track

        val remoteFile = File(track.previewUrl)
        val fileName = "${track.trackId}.${remoteFile.extension}"
        val localFile = File("cache", fileName)

        /* If there is cache for this track */
        if (storageRepository.exists(fileName)) return DownloadTrackResponse(track, localFile)

        /* Download this track to cache */
        val downloadedFile =
            storageRepository.download(url = track.previewUrl, name = localFile.path)

        musicDatabase.tracks.markAsDownloaded(trackId = track.trackId)

        return DownloadTrackResponse(track, downloadedFile)
    }
}