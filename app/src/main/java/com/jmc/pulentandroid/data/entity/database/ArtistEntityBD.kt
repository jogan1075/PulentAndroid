package com.jmc.pulentandroid.data.entity.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jmc.pulentandroid.utils.TABLE_ARTISTS

/**
 * Created by Jmunoz on 2019-11-02.
 */

@Entity(tableName = TABLE_ARTISTS)
data class ArtistEntityBD(
    @PrimaryKey
    val artistId: Long,
    val artistName: String,
    val artistLinkUrl: String,
    val primaryGenreName: String,
    val primaryGenreId: Long,
    val like: Int,
    val queryString: String
)