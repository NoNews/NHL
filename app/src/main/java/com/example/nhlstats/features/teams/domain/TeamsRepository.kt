package com.example.nhlstats.features.teams.domain

import com.example.nhlstats.common.data.response.Data

interface TeamsRepository {

    suspend fun getTeams(): Data<List<ShortTeam>>

    suspend fun getTeam(id: Int): Data<ShortTeam>
}