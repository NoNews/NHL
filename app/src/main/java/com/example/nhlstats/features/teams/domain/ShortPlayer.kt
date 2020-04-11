package com.example.nhlstats.features.teams.domain

import java.io.Serializable

data class ShortPlayer(
    val id: Int,
    val fullName: String,
    val link: String,
    val jerseyNumber: String?,
    val positionInfo: PositionInfo
) : Serializable