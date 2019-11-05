package com.jmc.pulentandroid.presentation.ui.searchArtist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jmc.pulentandroid.R
import com.jmc.pulentandroid.domain.model.Artist
import com.jmc.pulentandroid.presentation.ui.searchArtist.ArtistAdapterManager
import com.jmc.pulentandroid.utils.base.BaseAdapter
import com.jmc.pulentandroid.utils.base.BaseViewHolder
import com.jmc.pulentandroid.utils.onClickOnce


/**
 * Created by Jmunoz on 2019-10-31.
 */
open class ArtistAdapter(
    private val manager: ArtistAdapterManager
) : BaseAdapter<Artist>() {


    override fun provideComparator() = compareBy(Artist::artistId)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Artist> {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_artist, parent, false)

        return ArtistViewHolder(itemView).also {
            with(itemView) {
                onClickOnce {
                    val item = it.resolveItem()

                    if (item != null)
                        manager.onArtistClicked(item, it.adapterPosition)
                }

            }
        }
    }
}
