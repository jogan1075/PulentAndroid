package com.jmc.pulentandroid.data.datasource.remote.api

import com.jmc.pulentandroid.data.entity.api.ArtistEntity
import com.jmc.pulentandroid.data.entity.api.SearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Jmunoz on 2019-10-31.
 */

interface iTunesSearchApi {
    @GET("search")
    fun searchArtists(
        @Query("term") terms: String,
        @Query("entity") entity: String = "musicArtist"
    ): Call<SearchResult<ArtistEntity>>


}