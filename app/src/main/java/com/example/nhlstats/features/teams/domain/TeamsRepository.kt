package com.example.nhlstats.features.teams.domain

import com.example.nhlstats.common.data.response.Data
import com.example.nhlstats.features.teams.data.ShortTeam

interface TeamsRepository {
    suspend fun getShortTeams(): Data<List<ShortTeam>>
}