package com.example.nhlstats

import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.nhlstats.features.teams.presentation.mvi.list.TeamsFragment

class MainActivity : AppCompatActivity(), Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, TeamsFragment())
                .commit()
        }
    }

    override fun navigate(fragment: Fragment, params: Parcelable?) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(android.R.id.content, fragment)
            .commit()
    }
}

interface Navigator {
    fun navigate(fragment: Fragment, params: Parcelable? = null)
}

