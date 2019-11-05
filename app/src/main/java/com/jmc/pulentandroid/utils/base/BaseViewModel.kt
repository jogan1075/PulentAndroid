package com.jmc.pulentandroid.utils.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Jmunoz on 2019-10-31.
 */

abstract class BaseViewModel<T>(private val initState: T) : ViewModel() {
    val state = MutableLiveData<T>()

    init {
        state.value = initState
    }

    fun setState(update: T.() -> T) {
        val oldState = state.value
        val newState = oldState?.update()

        state.postValue(newState)
    }
}