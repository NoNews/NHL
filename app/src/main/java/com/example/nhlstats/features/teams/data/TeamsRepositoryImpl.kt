package com.example.nhlstats.features.teams.data

import com.example.nhlstats.common.data.cache.MemoryCache
import com.example.nhlstats.common.data.cache.TimeBasedCache
import com.example.nhlstats.common.data.response.Data
import com.example.nhlstats.common.data.response.DataDelegate
import com.example.nhlstats.features.teams.data.dto.TeamDto
import com.example.nhlstats.features.teams.data.network.TeamsService
import com.example.nhlstats.features.teams.data.response.TeamPlayersResponse
import com.example.nhlstats.features.teams.data.response.TeamsResponse
import com.example.nhlstats.features.teams.domain.*
import kotlinx.coroutines.flow.Flow


private const val TEAM_KEY = "teams_key"
private const val PLAYERS_KEY = "players_key"

class TeamsRepositoryImpl(private val teamsService: TeamsService) : TeamsRepository {
    private val memoryCache: MemoryCache<String> = TimeBasedCache.minutes(duration = 5, size = 50)
    private val teamsDelegate = DataDelegate(
        fromMemory = { _: Any ->
            memoryCache.get<List<ShortTeam>>(TEAM_KEY)
        },
        toMemory = { id, domain ->
            memoryCache[TEAM_KEY] = domain
        },
        fromNetwork = {
            teamsService.getAllTeams().toDomain()
        }
    )

    private val playersDelegate = DataDelegate<Int, ShortTeamPlayers>(
        fromMemory = { id ->
            memoryCache.get<ShortTeamPlayers>(PLAYERS_KEY + id)
        },
        toMemory = { id, domain ->
            memoryCache[PLAYERS_KEY + id] = domain
        },
        fromNetwork = { id ->
            teamsService.getShortPlayers(id).toDomain()
        }
    )


    override suspend fun getTeams(): Flow<Data<List<ShortTeam>>> {
        return teamsDelegate.get(Unit, forceReload = false)
    }

    override suspend fun getTeam(id: Int): Data<ShortTeam> {
        return throw IllegalAccessError()
    }

    override suspend fun getPlayers(id: Int): Flow<Data<ShortTeamPlayers>> =
        playersDelegate.get(id, forceReload = false)

}

private fun TeamPlayersResponse.toDomain(): ShortTeamPlayers {

    val team = teams.first()

    val players =
        team.roster
            .nested
            .map { dto ->
                ShortPlayer(
                    id = dto.personDto.id,
                    fullName = dto.personDto.fullName,
                    jerseyNumber = dto.jerseyNumber ?: "",
                    link = dto.personDto.link,
                    positionInfo = when (dto.position.type) {
                        "Forward" -> PositionInfo.Forward(
                            type = PositionType.FORWARD,
                            forwardType = when (dto.position.code) {
                                "C" -> ForwardType.CENTER
                                "L" -> ForwardType.LEFT_WING
                                "R" -> ForwardType.RIGHT_WING
                                else -> error("")
                            }
                        )
                        "Defenseman" -> PositionInfo.Defenceman(PositionType.DEFENSEMAN)
                        "Goalie" -> PositionInfo.Goalie(PositionType.GOALIE)
                        else -> error("")
                    }
                )
            }

    val teamName = team.teamName

    return ShortTeamPlayers(teamName = teamName, players = players)
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




