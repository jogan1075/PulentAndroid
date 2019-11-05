package com.jmc.pulentandroid.data.datasource.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jmc.pulentandroid.data.entity.database.AlbumEntityBD

/**
 * Created by Jmunoz on 2019-11-02.
 */
@Dao
interface AlbumDao {
    @Query("SELECT * FROM albumes WHERE artistId = :artistId")
    suspend fun searchAlbums(artistId: Long): List<AlbumEntityBD>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAlbumsSearch(vararg albums: AlbumEntityBD)
}
