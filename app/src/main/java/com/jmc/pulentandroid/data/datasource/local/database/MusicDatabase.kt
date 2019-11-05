package com.jmc.pulentandroid.data.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jmc.pulentandroid.data.datasource.local.database.dao.AlbumDao
import com.jmc.pulentandroid.data.datasource.local.database.dao.ArtistDao
import com.jmc.pulentandroid.data.datasource.local.database.dao.TrackDao
import com.jmc.pulentandroid.data.entity.database.AlbumEntityBD
import com.jmc.pulentandroid.data.entity.database.ArtistEntityBD
import com.jmc.pulentandroid.data.entity.database.TrackEntityBD
import com.jmc.pulentandroid.utils.DATABASE_VERSION

/**
 * Created by Jmunoz on 2019-11-02.
 */


@Database(
    entities = [
        ArtistEntityBD::class,
        AlbumEntityBD::class,
        TrackEntityBD::class
    ],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class MusicDatabase : RoomDatabase() {
    abstract val artists: ArtistDao
    abstract val albums: AlbumDao
    abstract val tracks: TrackDao
}