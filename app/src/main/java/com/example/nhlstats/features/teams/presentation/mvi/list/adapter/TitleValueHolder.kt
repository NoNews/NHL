package com.example.nhlstats.features.teams.presentation.mvi.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nhlstats.R
import com.example.nhlstats.features.teams.domain.ShortTeam


class TitleValueHolder private constructor(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    private var tvTitle: TextView = itemView.findViewById(R.id.tv_team_title)
    private var tvSubtitle: TextView = itemView.findViewById(R.id.tv_team_subtitle)
    private var ltRoot: ViewGroup = itemView.findViewById(R.id.lt_team_root)

    private var listener: ((ShortTeam) -> Unit)? = null


    constructor(
        inflater: LayoutInflater,
        parent: ViewGroup,
        listener: ((ShortTeam) -> Unit)?
    ) : this(inflater.inflate(R.layout.item_title_value, parent, false)) {
        this.listener = listener

    }

    fun bind(team: ShortTeam) {
        tvTitle.text = team.name
        tvSubtitle.text = team.fistYearOfPlay
        ltRoot.setOnClickListener {
            listener?.invoke(team)
        }
    }

}