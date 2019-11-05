package com.jmc.pulentandroid.data.datasource.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jmc.pulentandroid.data.entity.database.TrackEntityBD

/**
 * Created by Jmunoz on 2019-11-02.
 */
@Dao
interface TrackDao {
    @Query("SELECT * FROM pistas WHERE collectionId = :albumId")
    suspend fun searchTracks(albumId: Long): List<TrackEntityBD>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTracksSearch(vararg tracks: TrackEntityBD)

    @Query("UPDATE pistas SET isDownloaded = 1 WHERE trackId = :trackId")
    suspend fun markAsDownloaded(trackId: Long)
}
