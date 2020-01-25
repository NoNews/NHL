package com.example.nhlstats.features.teams.data.network

import com.example.nhlstats.features.teams.data.response.TeamsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TeamsService {

    @GET("/api/v1/teams")
    fun getAllTeamsAsync(): Deferred<Response<TeamsResponse>>

    @GET("/api/v1/teams/{id}")
    fun getTeamAsync(@Path("id") id: Int): Deferred<Response<TeamsResponse>>

}