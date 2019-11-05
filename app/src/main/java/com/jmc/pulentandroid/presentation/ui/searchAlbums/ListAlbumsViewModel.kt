package com.jmc.pulentandroid.presentation.ui.searchAlbums

import androidx.lifecycle.ViewModel
import com.jmc.pulentandroid.domain.model.Album
import com.jmc.pulentandroid.domain.model.request.LookupAlbumsRequest
import com.jmc.pulentandroid.domain.usercase.SearchAlbumsUseCase
import com.jmc.pulentandroid.utils.LiveResult


/**
 * Created by Jmunoz
 */

open class ListAlbumsViewModel(
    private val searchAlbumsUseCase: SearchAlbumsUseCase
) : ViewModel() {
    val albums = LiveResult<List<Album>>()

    fun lookupAlbums(artistId: Long) {
        searchAlbumsUseCase.execute(
            liveData = albums,
            params = LookupAlbumsRequest(artistId = artistId)
        )
    }
}