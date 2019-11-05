package com.jmc.pulentandroid.presentation.ui.searchArtist

import android.net.ConnectivityManager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import com.jmc.pulentandroid.R
import com.jmc.pulentandroid.domain.model.Artist
import com.jmc.pulentandroid.presentation.state.SearchState
import com.jmc.pulentandroid.presentation.ui.searchAlbums.ListAlbumsActivity
import com.jmc.pulentandroid.presentation.ui.searchArtist.adapter.ArtistAdapter
import com.jmc.pulentandroid.utils.EXTRA_ARTIST_ID

import com.jmc.pulentandroid.utils.base.coroutines.Result
import com.jmc.pulentandroid.utils.isNetworkAvailable
import com.jmc.pulentandroid.utils.observe
import kotlinx.android.synthetic.main.main_fragment.*
import org.jetbrains.anko.support.v4.longToast
import org.jetbrains.anko.support.v4.startActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(),ArtistAdapterManager {

    private val connectionManager: ConnectivityManager by inject()

    private val viewModel: MainViewModel by viewModel()

    private val artistAdapter = ArtistAdapter(this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(rViewSearch) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(requireContext())
            adapter = artistAdapter

            setHasFixedSize(true)
        }


        searching(searchView)

        with(viewModel) {
            observe(state, ::stateObserver)
            observe(artists, ::artistsObserver)
        }
    }

    private fun searching(search: androidx.appcompat.widget.SearchView) {
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchArtists(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                return true
            }
        })

    }


    private fun stateObserver(state: SearchState?) {
        state ?: return

        pBarSearch.visibility = View.VISIBLE

        val query = state.query

        if (query.isNotBlank())
            viewModel.searchArtists(term = query)
        else
            viewModel.resetSearch()
    }


    private fun artistsObserver(result: Result<List<Artist>>?) {
        when (result) {
            is Result.OnLoading -> {
                pBarSearch.visibility = View.VISIBLE
            }
            is Result.OnSuccess -> {
                pBarSearch.visibility = View.GONE

                val artists = result.value
                    .sortedBy { it.artistName }

                if (artists.isNotEmpty()) {
                    rViewSearch.visibility = View.VISIBLE
                } else {
                    rViewSearch.visibility = View.GONE
                }

                artistAdapter.swapItems(new = artists)
            }
            is Result.OnError -> {
                pBarSearch.visibility = View.GONE

                if (connectionManager.isNetworkAvailable())
                    longToast("conexion fallida al servidor")
                else
                    longToast("error de conexion, revisa tu internet e intenta nuevamente")
            }
            else -> {
                pBarSearch.visibility = View.GONE
            }
        }
    }

    override fun onArtistClicked(item: Artist, position: Int) {
        startActivity<ListAlbumsActivity>(EXTRA_ARTIST_ID to item.artistId)
    }
}
