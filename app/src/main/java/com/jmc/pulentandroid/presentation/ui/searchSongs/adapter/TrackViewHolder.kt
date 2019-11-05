package com.jmc.pulentandroid.presentation.ui.searchSongs.adapter

import android.view.View
import com.jmc.pulentandroid.presentation.modelView.TrackItem
import com.jmc.pulentandroid.utils.base.BaseViewHolder
import com.jmc.pulentandroid.utils.drawables
import com.jmc.pulentandroid.utils.visible
import kotlinx.android.synthetic.main.item_track.view.*
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.textColorResource

/**
 * Created by Jmunoz on 2019-11-01.
 */

open class TrackViewHolder(
    itemView: View
) : BaseViewHolder<TrackItem>(itemView) {
    override fun bindView(item: TrackItem) {
        with(itemView) {
            tViewTrackName.drawables(right = item.nameIconResource)

            val textColorResource = item.computeTextColorResource()
            val actionIconResource = item.computeActionIconResource()

            tViewTrackName.textColorResource = textColorResource
            tViewTrackTime.textColorResource = textColorResource
            iViewTrackAction.imageResource = actionIconResource

            tViewTrackName.text = item.trackName
            tViewTrackTime.text = item.timeMillisString

            pBarTrack.visible = item.isDownloading
            iViewTrackAction.visible = !item.isDownloading

            if (actionIconResource != 0) iViewTrackAction.imageResource = actionIconResource
        }
    }
}