package com.example.nhlstats.features.teams.data.network

import com.example.nhlstats.features.teams.data.response.TeamPlayersResponse
import com.example.nhlstats.features.teams.data.response.TeamsResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TeamsService {

    @GET("/api/v1/teams")
    suspend fun getAllTeams(): TeamsResponse

    @GET("/api/v1/teams/{id}")
    suspend fun getTeamAsync(@Path("id") id: Int): Response<TeamsResponse>

    @GET("teams/{id}?expand=team.roster")
    suspend fun getShortPlayers(@Path("id") id: Int): TeamPlayersResponse

}