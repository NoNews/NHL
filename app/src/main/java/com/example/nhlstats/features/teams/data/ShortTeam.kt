package com.example.nhlstats.features.teams.data

data class ShortTeam(
    val id: Int,
    val name: String,
    val link: String,
    val abbreviation: String,
    val teamName: String,
    val locationName: String,
    val fistYearOfPlay: String
)