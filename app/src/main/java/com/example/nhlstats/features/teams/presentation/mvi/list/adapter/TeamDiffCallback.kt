package com.example.nhlstats.features.teams.presentation.mvi.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.nhlstats.features.teams.data.ShortTeam

class TeamDiffCallback : DiffUtil.ItemCallback<ShortTeam>() {

    override fun areItemsTheSame(oldItem: ShortTeam, newItem: ShortTeam): Boolean {
        return oldItem.abbreviation == newItem.abbreviation
    }

    override fun areContentsTheSame(oldItem: ShortTeam, newItem: ShortTeam): Boolean {
        return oldItem == newItem
    }
}