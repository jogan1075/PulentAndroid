package com.jmc.pulentandroid.presentation.ui.searchArtist

import com.jmc.pulentandroid.domain.model.Artist
import com.jmc.pulentandroid.domain.model.request.SearchArtistsRequest
import com.jmc.pulentandroid.domain.usercase.SearchArtistsUseCase
import com.jmc.pulentandroid.presentation.state.SearchState
import com.jmc.pulentandroid.utils.LiveResult
import com.jmc.pulentandroid.utils.base.BaseViewModel
import com.jmc.pulentandroid.utils.postSuccess

open class MainViewModel(
    private val searchArtistsUseCase: SearchArtistsUseCase
) : BaseViewModel<SearchState>(initState = SearchState()) {
    val artists = LiveResult<List<Artist>>()


    fun searchArtists(term: String) {
        val request = SearchArtistsRequest(query = term)

        searchArtistsUseCase.execute(liveData = artists, params = request)
    }

    fun resetSearch() {
        artists.postSuccess(listOf())
    }

}