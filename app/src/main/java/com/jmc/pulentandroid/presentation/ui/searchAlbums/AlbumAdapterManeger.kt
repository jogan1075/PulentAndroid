package com.jmc.pulentandroid.presentation.ui.searchAlbums

import com.jmc.pulentandroid.domain.model.Album
import com.squareup.picasso.Picasso


/**
 * Created by Jmunoz
 */

interface AlbumAdapterManeger {

    fun onAlbumClicked(item: Album, position: Int)

    fun provideImageLoader(): Picasso
}