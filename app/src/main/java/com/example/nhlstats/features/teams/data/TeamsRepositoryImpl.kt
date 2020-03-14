package com.example.nhlstats.features.teams.data

import com.example.nhlstats.common.data.cache.MemoryCache
import com.example.nhlstats.common.data.cache.TimeBasedCache
import com.example.nhlstats.common.data.response.Data
import com.example.nhlstats.common.data.response.DataDelegate
import com.example.nhlstats.features.teams.data.dto.TeamDto
import com.example.nhlstats.features.teams.data.network.TeamsService
import com.example.nhlstats.features.teams.data.response.TeamsResponse
import com.example.nhlstats.features.teams.domain.ShortPlayer
import com.example.nhlstats.features.teams.domain.ShortTeam
import com.example.nhlstats.features.teams.domain.TeamsRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow


private const val TEAM_KEY = "eams_key"

class TeamsRepositoryImpl(private val teamsService: TeamsService) : TeamsRepository {


    private val memoryCache: MemoryCache<String> = TimeBasedCache.minutes(duration = 5, size = 50)


    private val teamsDelegate = DataDelegate(
        fromMemory = { _: Any ->
            memoryCache.get<List<ShortTeam>>(TEAM_KEY)
        },
        fromNetwork = {
            teamsService.getAllTeamsAsync().toDomain()
        },
        toMemory = { _, domain ->
            memoryCache[TEAM_KEY] = domain
        }
    )


    override suspend fun getTeams(): Flow<Data<List<ShortTeam>>> {
        return teamsDelegate.get(Unit, forceReload = false)
    }

    override suspend fun getTeam(id: Int): Data<ShortTeam> {

        return throw IllegalAccessError()

    }

    override suspend fun getPlayers(id: Int): Deferred<Data<List<ShortPlayer>>> {
//        val players = teamsService}.getShortPlayersAsync(id)
        throw IllegalStateException()
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




