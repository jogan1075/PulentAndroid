package com.jmc.pulentandroid.domain.repository

import java.io.File
import java.io.InputStream

/**
 * Created by Jmunoz on 2019-11-01.
 */

interface StorageRepository {
    suspend fun download(url: String, name: String): File
    suspend fun get(name: String): InputStream
    suspend fun exists(name: String): Boolean
    suspend fun delete(name: String)
}