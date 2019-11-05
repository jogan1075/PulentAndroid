package com.jmc.pulentandroid.presentation.ui.searchArtist.adapter

import android.view.View
import com.jmc.pulentandroid.domain.model.Artist
import com.jmc.pulentandroid.utils.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_artist.view.*

/**
 * Created by Jmunoz on 2019-10-31.
 */

open class ArtistViewHolder(
    itemView: View
) :
    BaseViewHolder<Artist>(itemView) {
    override fun bindView(item: Artist) {
        with(itemView) {
            tViewArtistName.text = item.artistName
            tViewArtistGenre.text = item.primaryGenreName

        }
    }
}