package com.jmc.pulentandroid.data.datasource.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jmc.pulentandroid.data.entity.database.ArtistEntityBD

/**
 * Created by Jmunoz on 2019-11-02.
 */
@Dao
interface ArtistDao {
    @Query("SELECT * FROM artistas WHERE artistName LIKE ('%' || :term || '%') OR queryString LIKE ('%' || :term || '%')")
    suspend fun searchArtists(term: String): List<ArtistEntityBD>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArtistsSearch(vararg artists: ArtistEntityBD)


}
