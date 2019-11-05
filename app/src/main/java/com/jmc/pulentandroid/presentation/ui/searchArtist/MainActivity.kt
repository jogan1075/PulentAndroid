package com.jmc.pulentandroid.presentation.ui.searchArtist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jmc.pulentandroid.R

class MainActivity : AppCompatActivity() {

    private val mainFragment by lazy {
        MainFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, mainFragment)
                .commit()
        }

    }

}
