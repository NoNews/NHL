package com.example.nhlstats.features.teams.data.dto

import com.example.nhlstats.common.data.response.Data
import com.example.nhlstats.common.data.response.HttpResponseWrapper
import com.example.nhlstats.common.data.response.toData
import com.example.nhlstats.features.teams.data.ShortTeam
import com.example.nhlstats.features.teams.data.network.TeamsService
import com.example.nhlstats.features.teams.domain.TeamsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class TeamsRepositoryImpl(private val teamsService: TeamsService) : TeamsRepository {


    private val responseWrapper = HttpResponseWrapper<TeamsResponse>()

//    private val delegate = RequestDelegate<Unit, List<Team>>(
////        fromNetwork = {
//////            teamsService.getAllTeamsAsync().await()
//////            responseWrapper.wrap(teamsService.getAllTeamsAsync())
//////                .toDomain()
////
////
////        }
////        fromMemory = { null },
////        toMemory = { unit: Unit, list: List<Team> -> }
//    )

    override suspend fun getShortTeams(): Data<List<ShortTeam>> {
        return withContext(Dispatchers.IO) {
            val response = teamsService.getAllTeamsAsync()
                .await()
            if (response.isSuccessful) {

                requireNotNull(response.body())
                    .toDomain()
                    .toData()
            } else {
                requireNotNull(response.body())
                    .toDomain()
                    .toData()
            }
        }
    }


}


private fun TeamsResponse.toDomain() = teams.map { dto ->
    ShortTeam(
        id = dto.id ?: Integer.MAX_VALUE,
        name = dto.name ?: "",
        link = dto.link ?: "",
        abbreviation = dto.abbreviation ?: "",
        teamName = dto.teamName ?: "",
        locationName = dto.locationName ?: "",
        fistYearOfPlay = dto.firstYearOfPlay ?: ""
    )
}



