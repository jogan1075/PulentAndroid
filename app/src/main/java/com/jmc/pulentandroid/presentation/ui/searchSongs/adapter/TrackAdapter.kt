package com.jmc.pulentandroid.presentation.ui.searchSongs.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jmc.pulentandroid.R
import com.jmc.pulentandroid.presentation.modelView.TrackItem
import com.jmc.pulentandroid.utils.base.BaseAdapter
import com.jmc.pulentandroid.utils.base.BaseViewHolder
import com.jmc.pulentandroid.utils.onClickOnce

/**
 * Created by Jmunoz on 2019-11-01.
 */


open class TrackAdapter(
    private val manager: AdapterManager
) : BaseAdapter<TrackItem>() {

    interface AdapterManager {
        fun onPlayTrackClicked(track: TrackItem, position: Int)
        fun onPauseTrackClicked(track: TrackItem, position: Int)
    }

    override fun provideComparator() = compareBy(TrackItem::trackId)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<TrackItem> {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_track, parent, false)

        return TrackViewHolder(itemView).also {
            with(itemView) {
                onClickOnce {
                    val item = it.resolveItem()

                    if (item != null)
                        when {
                            item.isMusic -> {
                                if (item.isPlaying)
                                    manager.onPauseTrackClicked(track = item, position = it.adapterPosition)
                                else
                                    manager.onPlayTrackClicked(track = item, position = it.adapterPosition)
                            }
                        }
                }
            }
        }
    }

    override fun updateItem(position: Int, update: TrackItem.() -> TrackItem) {
        resetStates()

        super.updateItem(position, update)
    }

    fun resetStates() {
        items.mapIndexedNotNull { index, track ->
            if (track.isPlaying || track.isPaused || track.isDownloading)
                index
            else
                null
        }.forEach { index ->
            items[index] = items[index].copy(
                isPlaying = false,
                isPaused = false,
                isDownloading = false
            )

            notifyItemChanged(index)
        }
    }
}