package com.jmc.pulentandroid.presentation.ui.searchSongs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import com.jmc.pulentandroid.R
import com.jmc.pulentandroid.domain.model.Track
import com.jmc.pulentandroid.domain.model.response.DownloadTrackResponse
import com.jmc.pulentandroid.presentation.modelView.TrackItem
import com.jmc.pulentandroid.presentation.ui.searchSongs.adapter.TrackAdapter
import com.jmc.pulentandroid.utils.*
import com.jmc.pulentandroid.utils.base.coroutines.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_album.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailAlbumActivity : AppCompatActivity() {


    private val viewModel: DetailAlbumViewModel by viewModel()

    private val trackAdapter = TrackAdapter(manager = TrackManager())

    private lateinit var mediaPlayerManager: MediaPlayerManager

    private val picasso: Picasso by inject()


    private val extraAlbumId by lazy {
        intent.getLongExtra(EXTRA_ALBUM_ID, 0)
    }

    private val extraUrlImg by lazy {
        intent.getStringExtra(EXTRA_URL_IMG)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_album)

        with(rViewAlbumDetail) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            addItemDecoration(
                androidx.recyclerview.widget.DividerItemDecoration(
                    context,
                    androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
                )
            )
            adapter = trackAdapter
            setHasFixedSize(true)
        }

        with(viewModel) {
            observe(tracks, ::tracksObserver)
            observe(download, ::downloadObserver)

            lookupTracks(albumId = extraAlbumId)
        }


        with(picasso) {
            picasso.load(extraUrlImg).into(imageView2)
            imageView2.updateLayoutParams<ViewGroup.LayoutParams> {
                width = 400
                height = 400
            }
        }
    }


    override fun onResume() {
        super.onResume()

        mediaPlayerManager = MediaPlayerManager(this)
    }

    override fun onPause() {
        mediaPlayerManager.release()

        trackAdapter.resetStates()

        super.onPause()
    }

    override fun onStop() {
        mediaPlayerManager.release()

        super.onStop()
    }

    private fun tracksObserver(result: Result<List<Track>>?) {
        when (result) {
            is Result.OnLoading -> {
            }
            is Result.OnSuccess -> {


                val tracks = result.value

                if (tracks.isNotEmpty())
                    supportActionBar?.title = tracks.first().collectionName

                val items = tracks.map(Track::toTrackItem)

                trackAdapter.swapItems(new = items)
            }
            is Result.OnError -> {
            }

        }
    }

    private fun downloadObserver(result: Result<DownloadTrackResponse>?) {
        when (result) {
            is Result.OnLoading -> {

            }
            is Result.OnSuccess -> {
                val response = result.value

                val isReady = !response.track.isDownloading || response.track.isDownloaded

                if (isReady) {
                    val track = response.track.let(Track::toTrackItem)

                    trackAdapter.updateItem(track) {
                        copy(
                            isPlaying = true,
                            isPaused = false,
                            isDownloading = false,
                            isDownloaded = true
                        )
                    }

                    mediaPlayerManager.play(source = response.file) {
                        trackAdapter.updateItem(track) {
                            copy(
                                isPlaying = false,
                                isPaused = false,
                                isDownloading = false
                            )
                        }
                    }
                }
            }
            is Result.OnError -> {
            }
        }
    }

    inner class TrackManager : TrackAdapter.AdapterManager {
        override fun onPlayTrackClicked(track: TrackItem, position: Int) {
            mediaPlayerManager.stop()

            if (track.isDownloaded)
                trackAdapter.updateItem(position = position) {
                    copy(
                        isPlaying = true,
                        isPaused = false,
                        isDownloading = false
                    )
                }
            else
                trackAdapter.updateItem(position = position) {
                    copy(
                        isPlaying = false,
                        isPaused = false,
                        isDownloading = true
                    )
                }

            viewModel.downloadTrack(track = track.let(TrackItem::toTrack))
        }

        override fun onPauseTrackClicked(track: TrackItem, position: Int) {
            mediaPlayerManager.pause()

            trackAdapter.updateItem(position = position) {
                copy(
                    isPlaying = false,
                    isPaused = true,
                    isDownloading = false
                )
            }
        }


    }


}

