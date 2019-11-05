package com.jmc.pulentandroid.domain.model.response

import com.jmc.pulentandroid.domain.model.Track
import java.io.File

/**
 * Created by Jmunoz on 2019-11-01.
 */

data class DownloadTrackResponse(
    val track: Track,
    val file: File
)