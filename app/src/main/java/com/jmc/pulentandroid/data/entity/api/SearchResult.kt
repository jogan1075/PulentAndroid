package com.jmc.pulentandroid.data.entity.api

/**
 * Created by Jmunoz on 2019-10-31.
 */

data class SearchResult<T>(
    val resultCount: Int,
    val results: List<T>
)