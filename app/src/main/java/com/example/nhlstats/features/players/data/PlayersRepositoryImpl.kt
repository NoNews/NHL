package com.example.nhlstats.features.players.data

import com.example.nhlstats.common.data.response.Data
import com.example.nhlstats.features.players.domain.Player
import com.example.nhlstats.features.players.domain.PlayersRepository

class PlayersRepositoryImpl : PlayersRepository {
    override fun getPlayer(): Data<Player> {
        TODO()
    }
}