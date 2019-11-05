package com.jmc.pulentandroid.presentation.ui.searchAlbums.adapter

import android.view.View
import com.jmc.pulentandroid.R
import com.jmc.pulentandroid.domain.model.Album
import com.jmc.pulentandroid.presentation.ui.searchAlbums.AlbumAdapterManeger
import com.jmc.pulentandroid.utils.base.BaseViewHolder
import com.jmc.pulentandroid.utils.drawables
import com.jmc.pulentandroid.utils.formatYear
import kotlinx.android.synthetic.main.item_album.view.*
import org.jetbrains.anko.imageResource

/**
 * Created by Jmunoz on 2019-10-31.
 */

open class AlbumViewHolder(
    itemView: View,
    private val manager: AlbumAdapterManeger
) : BaseViewHolder<Album>(itemView) {
    override fun bindView(item: Album) {
        with(itemView) {
            tViewAlbumName.text = item.collectionName
            tViewAlbumName.drawables(right = if (item.isExplicit) R.drawable.ic_explicit else 0)
            tViewAlbumArtistName.text = item.artistName
            tViewAlbumGenreAndYear.text = context.getString(
                R.string.text_album_genre_year,
                item.primaryGenreName,
                item.releaseDate.formatYear()
            )

            val artworkUrl = item.artworkUrl100

            if (artworkUrl.isNotBlank())
                with(receiver = manager.provideImageLoader()) {
                    load(artworkUrl)
                        .placeholder(R.mipmap.ic_launcher_round)
                        .into(iViewAlbumCover)
                }
            else
                iViewAlbumCover.imageResource = R.mipmap.ic_launcher_round
        }
    }
}