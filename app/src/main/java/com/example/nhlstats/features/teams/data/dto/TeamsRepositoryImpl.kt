package com.example.nhlstats.features.teams.data.dto

import com.example.nhlstats.common.data.response.Data
import com.example.nhlstats.common.data.response.toData
import com.example.nhlstats.features.teams.data.network.TeamsService
import com.example.nhlstats.features.teams.data.response.TeamsResponse
import com.example.nhlstats.features.teams.domain.ShortTeam
import com.example.nhlstats.features.teams.domain.TeamsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class TeamsRepositoryImpl(private val teamsService: TeamsService) : TeamsRepository {

    override suspend fun getTeams(): Data<List<ShortTeam>> {
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

    override suspend fun getTeam(id: Int): Data<ShortTeam> {

        return withContext(Dispatchers.IO) {
            val response = teamsService.getTeamAsync(id).await()

            if (response.isSuccessful) {
                requireNotNull(response.body()).toDomain().first().toData()
            } else {
                requireNotNull(response.body()).toDomain().first().toData()
            }
        }

    }


}


private fun TeamsResponse.toDomain() = teams.map { dto ->
    dto.toDomain()
}

private fun TeamDto.toDomain(): ShortTeam {
    return ShortTeam(
        id = id ?: Integer.MAX_VALUE,
        name = name ?: "",
        link = link ?: "",
        abbreviation = abbreviation ?: "",
        teamName = teamName ?: "",
        locationName = locationName ?: "",
        fistYearOfPlay = firstYearOfPlay ?: ""
    )
}




