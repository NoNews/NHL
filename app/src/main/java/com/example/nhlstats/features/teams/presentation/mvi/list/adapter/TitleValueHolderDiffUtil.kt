package com.example.nhlstats.features.teams.presentation.mvi.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.core_ui.list.ui.delegates.TitleValueItem
import com.example.nhlstats.features.teams.domain.ShortTeam

class TitleValueHolderDiffUtil : DiffUtil.ItemCallback<TitleValueItem>() {

    override fun areItemsTheSame(oldItem: TitleValueItem, newItem: TitleValueItem): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: TitleValueItem, newItem: TitleValueItem): Boolean {
        return oldItem == newItem
    }
}