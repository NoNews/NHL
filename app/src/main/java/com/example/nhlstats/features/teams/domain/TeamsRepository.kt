package com.example.nhlstats.features.teams.domain

import com.example.nhlstats.common.data.response.Data
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow

interface TeamsRepository {

    suspend fun getTeams(): Flow<Data<List<ShortTeam>>>

    suspend fun getTeam(id: Int): Data<ShortTeam>


    suspend fun getPlayers(id: Int): Deferred<Data<List<ShortPlayer>>>
}