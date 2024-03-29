package com.jmc.pulentandroid.utils


import android.net.ConnectivityManager
import android.view.View
import android.widget.TextView

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.*
import com.jmc.pulentandroid.utils.base.coroutines.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.text.Normalizer
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * Created by Jmunoz on 2019-10-31.
 */


fun ConnectivityManager.isNetworkAvailable() = activeNetworkInfo?.isConnected == true


/* Click - Likstener*/
fun View.onClickOnce(onClick: () -> Unit) {
    setOnClickListener(object : View.OnClickListener {
        override fun onClick(view: View) {
            view.setOnClickListener(null)

            also { listener ->
                CoroutineScope(Dispatchers.Main).launch {
                    onClick()

                    withContext(Dispatchers.IO) { delay(500) }

                    view.setOnClickListener(listener)
                }
            }
        }
    })
}

/* Live data */

typealias LiveResult<T> = MutableLiveData<Result<T>>

/* LiveResult */

@JvmName("postCompleteResult")
fun <T> LiveResult<T>.postSuccess(value: T) = postValue(Result.OnSuccess(value))

@JvmName("postThrowableResult")
fun <T> LiveResult<T>.postThrowable(throwable: Throwable) = postValue(Result.OnError(throwable))

@JvmName("postLoadingResult")
fun <T> LiveResult<T>.postLoading() = postValue(Result.OnLoading())

@JvmName("postCancelResult")
fun <T> LiveResult<T>.postCancel() = postValue(Result.OnCancel())

@JvmName("postEmptyResult")
fun <T> LiveResult<T>.postEmpty() = postValue(Result.OnEmpty())


/* Coroutines */

suspend fun <T> Call<T>.await() = suspendCoroutine<T?> { continuation ->
    enqueue(object : Callback<T?> {
        override fun onResponse(call: Call<T?>, response: Response<T?>) {
            if (response.isSuccessful)
                continuation.resume(response.body())
            else
                continuation.resumeWithException(HttpException(response))
        }

        override fun onFailure(call: Call<T?>, t: Throwable) {
            continuation.resumeWithException(t)
        }
    })
}


/* Observers */

fun <T, L : LiveData<T>> FragmentActivity.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))

fun <T, L : LiveData<T>> Fragment.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(viewLifecycleOwner, Observer(body))


private val ISO8601format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
fun String.parseISO8601Date(): Date = ISO8601format.parse(this)

private val yearFormat = SimpleDateFormat("yyyy", Locale.US)
fun Date.formatYear(): String = yearFormat.format(this)


fun TextView.drawables(
    left: Int = 0,
    top: Int = 0,
    right: Int = 0,
    bottom: Int = 0
) = setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom)

private val intervalFormat = SimpleDateFormat("mm:ss", Locale.US)
fun Long.formatInterval(): String = intervalFormat.format(Date(this))


var View.visible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.INVISIBLE
    }

fun String.normalize() = Normalizer.normalize(toLowerCase(), Normalizer.Form.NFD).replace("[^\\p{ASCII}]".toRegex(), "")