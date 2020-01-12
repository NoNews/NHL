package com.example.nhlstats.features.teams.presentation.mvi.list

import androidx.compose.Model
import com.example.nhlstats.features.teams.data.ShortTeam

@Model
data class TeamsState constructor(
    var progress: Boolean = false,
    var error: Throwable? = null,
    var data: List<ShortTeam>? = null
) {
    companion object {
        fun createDefault() =
            TeamsState()
    }


    fun rendredProgress() {
        progress = true
        error = null
        data = null
    }

    fun renderError(throwable: Throwable) {
        progress = false
        error = throwable
        data = null
    }

    fun renderContent(data: List<ShortTeam>) {
        progress = false
        error = null
        this.data = data
    }


    fun isDefault() = error == null && data == null && !progress
}