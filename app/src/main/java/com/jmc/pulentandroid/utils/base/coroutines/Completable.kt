package com.jmc.pulentandroid.utils.base.coroutines

/**
 * Created by Jmunoz on 2019-10-31.
 */
sealed class Completable {
    object OnComplete : Completable()
    data class OnError(val throwable: Throwable) : Completable()
    object OnLoading : Completable()
    object OnCancel : Completable()
}