package com.example.nhlstats.features.teams.data.network

import com.example.nhlstats.features.teams.data.dto.TeamsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface TeamsService {

    @GET("/api/v1//teams")
    fun getAllTeamsAsync(): Deferred<Response<TeamsResponse>>
}