package com.example.nhlstats.features.teams.presentation.mvi.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.nhlstats.features.teams.domain.ShortTeam

class TeamsListAdapter(
    private val inflater: LayoutInflater,
    diffCallback: DiffUtil.ItemCallback<ShortTeam>
) : ListAdapter<ShortTeam, TitleValueHolder>(diffCallback) {

    private var teamClickListener: ((ShortTeam) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleValueHolder {
        return TitleValueHolder(inflater, parent, teamClickListener)
    }

    override fun onBindViewHolder(holder: TitleValueHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    fun onClickTeam(function: (ShortTeam) -> Unit) {
        this.teamClickListener = function
    }
}

