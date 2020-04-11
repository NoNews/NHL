package com.example.nhlstats.features.players.domain

import com.example.nhlstats.common.data.response.Data

interface PlayersRepository {

    fun getPlayer(): Data<Player>
}