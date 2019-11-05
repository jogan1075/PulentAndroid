package com.jmc.pulentandroid.presentation.ui.searchAlbums

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jmc.pulentandroid.R
import com.jmc.pulentandroid.domain.model.Album
import com.jmc.pulentandroid.presentation.ui.searchAlbums.adapter.AlbumAdapter
import com.jmc.pulentandroid.presentation.ui.searchSongs.DetailAlbumActivity
import com.jmc.pulentandroid.utils.EXTRA_ALBUM_ID
import com.jmc.pulentandroid.utils.EXTRA_ARTIST_ID
import com.jmc.pulentandroid.utils.EXTRA_URL_IMG
import com.jmc.pulentandroid.utils.base.coroutines.Result
import com.jmc.pulentandroid.utils.observe
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_list_albums.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.onRefresh
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListAlbumsActivity : AppCompatActivity(), AlbumAdapterManeger {

    private val viewModel: ListAlbumsViewModel by viewModel()

    private val picasso: Picasso by inject()

    private val albumAdapter = AlbumAdapter(this)

    private val extraArtistId by lazy {
        intent.getLongExtra(EXTRA_ARTIST_ID, 0)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_albums)

        with(rViewAlbums) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            adapter = albumAdapter
            setHasFixedSize(true)
        }

        with(viewModel) {
            observe(albums, ::albumsObserver)

            lookupAlbums(artistId = extraArtistId)

            sRefreshAlbums.onRefresh {
                lookupAlbums(artistId = extraArtistId)
            }
        }
    }

    private fun albumsObserver(result: Result<List<Album>>?) {
        when (result) {
            is Result.OnLoading -> {
                sRefreshAlbums.isRefreshing = true
            }
            is Result.OnSuccess -> {
                sRefreshAlbums.isRefreshing = false

                val albums = result.value
                    .sortedByDescending { it.releaseDate }

                if (albums.isNotEmpty()) {
                    supportActionBar?.title = albums.first().artistName

                    tViewAlbums.visibility = View.GONE
                    rViewAlbums.visibility = View.VISIBLE
                } else {
                    rViewAlbums.visibility = View.GONE
                    tViewAlbums.visibility = View.VISIBLE
                }

                albumAdapter.swapItems(new = albums)
            }
            is Result.OnError -> {
                sRefreshAlbums.isRefreshing = false

            }
            else -> {
                sRefreshAlbums.isRefreshing = false

            }
        }
    }

    override fun onAlbumClicked(item: Album, position: Int) {
        startActivity<DetailAlbumActivity>(
            EXTRA_ALBUM_ID to item.collectionId,
            EXTRA_URL_IMG to item.artworkUrl100
        )
    }

    override fun provideImageLoader(): Picasso {
        return picasso
    }
}
