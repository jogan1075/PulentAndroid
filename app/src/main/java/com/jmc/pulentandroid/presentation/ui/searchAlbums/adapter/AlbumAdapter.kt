package com.jmc.pulentandroid.presentation.ui.searchAlbums.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import com.jmc.pulentandroid.R
import com.jmc.pulentandroid.domain.model.Album
import com.jmc.pulentandroid.utils.base.BaseAdapter
import com.jmc.pulentandroid.utils.base.BaseViewHolder
import com.jmc.pulentandroid.utils.onClickOnce
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_album.view.*

/**
 * Created by Jmunoz on 2019-10-31.
 */

open class AlbumAdapter(
    private val manager: AdapterManager
) : BaseAdapter<Album>() {

    interface AdapterManager {
        fun onAlbumClicked(item: Album, position: Int)

        fun provideImageLoader(): Picasso
    }

    override fun provideComparator() = compareBy(Album::collectionId)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Album> {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)

        with(itemView) {
            iViewAlbumCover.updateLayoutParams<ViewGroup.LayoutParams> {
                width = 200
                height = 200
            }
        }

        return AlbumViewHolder(itemView, manager).also {
            with(itemView) {
                onClickOnce {
                    val item = it.resolveItem()

                    if (item != null)
                        manager.onAlbumClicked(item, it.adapterPosition)
                }
            }
        }
    }
}