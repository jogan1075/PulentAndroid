package com.jmc.pulentandroid.presentation.ui.searchArtist

import com.jmc.pulentandroid.domain.model.Artist


/**
 * Created by Jmunoz
 */

interface ArtistAdapterManager {

    fun onArtistClicked(item: Artist, position: Int)
}