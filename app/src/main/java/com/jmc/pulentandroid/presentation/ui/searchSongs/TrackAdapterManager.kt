package com.jmc.pulentandroid.presentation.ui.searchSongs

import com.jmc.pulentandroid.presentation.modelView.TrackItem


/**
 * Created by Jmunoz
 */

interface TrackAdapterManager {

    fun onPlayTrackClicked(track: TrackItem, position: Int)
    fun onPauseTrackClicked(track: TrackItem, position: Int)
}