package com.example.nhlstats.features.players.domain

import java.util.*

data class Player(
    val id: Int,
    val fullName: String,
    val primaryNumber: String,
    val birthDate: Date,
    val currentAge: String,
    val birthCity: String,
    val nationality: String,
    val height: String,
    val weight: String,
    val active: Boolean,
    val rookie: Boolean,
    val role: PlayerRole
)