package com.jmc.pulentandroid.di

import android.content.Context
import android.net.ConnectivityManager
import com.jmc.pulentandroid.data.datasource.remote.RemoteDataStore
import com.jmc.pulentandroid.data.datasource.remote.api.iTunesSearchApi
import com.jmc.pulentandroid.domain.repository.RemoteRepository
import com.jmc.pulentandroid.domain.usercase.SearchArtistsUseCase
import com.jmc.pulentandroid.presentation.ui.searchArtist.MainViewModel
import com.jmc.pulentandroid.utils.URL_API
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.dsl.module
import org.koin.experimental.builder.factory
import org.koin.experimental.builder.factoryBy
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by Jmunoz on 2019-10-31.
 */

val appModule = module {

    /* Android Services */

    single {
        androidContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    /* Retrofit */

    single {
        OkHttpClient.Builder()
            .callTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(URL_API)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>()
            .create(iTunesSearchApi::class.java) as iTunesSearchApi
    }


    /* Picasso */

    single {
        Picasso.get()
    }


    /* View models */
    viewModel<MainViewModel>()


    /* Factories */

    factoryBy<RemoteRepository, RemoteDataStore>()


    /* Use cases */


    factory<SearchArtistsUseCase>()


}
