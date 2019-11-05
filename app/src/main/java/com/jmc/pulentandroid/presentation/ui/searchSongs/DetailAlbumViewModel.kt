package com.jmc.pulentandroid.presentation.ui.searchSongs

import androidx.lifecycle.ViewModel
import com.jmc.pulentandroid.domain.model.Track
import com.jmc.pulentandroid.domain.model.request.DownloadTrackRequest
import com.jmc.pulentandroid.domain.model.request.LookupTracksRequest
import com.jmc.pulentandroid.domain.model.response.DownloadTrackResponse
import com.jmc.pulentandroid.domain.usercase.DownloadTrackUseCase
import com.jmc.pulentandroid.domain.usercase.SearchTracksUseCase
import com.jmc.pulentandroid.utils.LiveResult


/**
 * Created by Jmunoz
 */

class DetailAlbumViewModel(
    private val searchTracksUseCase: SearchTracksUseCase,
    private val downloadTrackUseCase: DownloadTrackUseCase
) : ViewModel() {
    val tracks = LiveResult<List<Track>>()
    val download = LiveResult<DownloadTrackResponse>()

    fun lookupTracks(albumId: Long) {
        searchTracksUseCase.execute(
            liveData = tracks,
            params = LookupTracksRequest(albumId = albumId)
        )
    }

    fun downloadTrack(track: Track) {
        downloadTrackUseCase.execute(
            liveData = download,
            params = DownloadTrackRequest(track = track)
        )
    }
}