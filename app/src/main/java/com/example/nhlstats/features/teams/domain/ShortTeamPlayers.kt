package com.example.nhlstats.features.teams.domain

data class ShortTeamPlayers(
    val teamName: String,
    val players: List<ShortPlayer>
)