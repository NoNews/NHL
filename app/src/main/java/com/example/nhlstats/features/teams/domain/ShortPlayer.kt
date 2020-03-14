package com.example.nhlstats.features.teams.domain

data class ShortPlayer(
    val id: Int,
    val fullName: String,
    val link: String,
    val jerseyNumber: String?
)