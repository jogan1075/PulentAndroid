package com.jmc.pulentandroid.utils.base.coroutines

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

/**
 * Created by Jmunoz on 2019-10-31.
 */

abstract class BaseUseCase<Q, W : MutableLiveData<*>>(
    protected open val backgroundContext: CoroutineContext,
    protected open val foregroundContext: CoroutineContext
) {
    private var parentJob = Job()

    abstract fun execute(liveData: W, params: Q)

    protected fun newJob(): Job {
        parentJob = parentJob.run {
            cancelChildren()
            cancel()

            Job()
        }

        return parentJob
    }
}