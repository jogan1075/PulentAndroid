package com.jmc.pulentandroid.utils.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Jmunoz on 2019-10-31.
 */
abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bindView(item: T)
}