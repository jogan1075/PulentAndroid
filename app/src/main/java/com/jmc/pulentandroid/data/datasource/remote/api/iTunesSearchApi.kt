package com.jmc.pulentandroid.data.datasource.remote.api

import com.jmc.pulentandroid.data.entity.api.AlbumEntity
import com.jmc.pulentandroid.data.entity.api.ArtistEntity
import com.jmc.pulentandroid.data.entity.api.SearchResult
import com.jmc.pulentandroid.data.entity.api.TrackEntity
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

    @GET("lookup")
    fun lookupAlbums(
        @Query("id") artistId: Long,
        @Query("entity") entity: String = "album"
    ): Call<SearchResult<AlbumEntity>>


    @GET("lookup")
    fun lookupTracks(
        @Query("id") albumId: Long,
        @Query("entity") entity: String = "song"
    ): Call<SearchResult<TrackEntity>>
}